package com.jzone.tl_demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.algebra.sdk.API;
import com.algebra.sdk.DeviceApi;
import com.algebra.sdk.OnMediaListener;
import com.algebra.sdk.SessionApi;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.CompactID;
import com.algebra.sdk.entity.Constant;
import com.algebra.sdk.entity.HistoryRecord;
import com.algebra.sdk.entity.Sound;
import com.jzone.entity.MsgCode;
import com.jzone.utils.MediaKeys;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout.LayoutParams;
import android.util.Log;

import static com.jzone.tl_demo.TalkHistory.AMR475_PL_SIZE;

public class TalkFragment extends Fragment implements OnMediaListener,
		View.OnTouchListener, OnClickListener {
	private static final String TAG = "fragment.talk";
	private static final int FALSE = 0;
	private static final int TRUE = 1;
	// output_dev.xml Level:
	public static final int OUT_PHONEBODY = 0;
	public static final int OUT_BLUETOOTH_DISC = 1;
	public static final int OUT_BLUETOOTH_CONN = 2;
	public static final int OUT_BLUETOOTH_ERR = 4;
	public static final int OUT_BLUETOOTH_AERR = 5;

	private Context uiContext = null;
	private Handler uiHandler = null;
	private SessionApi sessionApi = null;
	private DeviceApi deviceApi = null;
	private AudioManager mAudioManager = null;
	private ComponentName mediaKeys = null;
	private TalkHistory talkHistory = null;

	private int selfId = 0;
	private CompactID currSession = null;
	private CompactID dispSession = null;
	private int selfStatus = Constant.CONTACT_STATE_ONLINE;
	private boolean isUndistube = false;
	private HistoryRecord newLastSpeaking = null;

	private ArrayList<Integer> waitList = new ArrayList<Integer>();
	private static boolean pttTriggerable = false;

	private TextView me_item = null;
	private ToggleButton ptt_button = null;
	private ImageView playlast_button = null;
	private ImageView ptt_mic_ind = null;
	private ImageView ptt_spk_ind = null;
	private ImageView level_ind = null;
	private TextView speaker_item = null;
	private TextView queuer_item = null;
	private ImageView record_ind = null;
	private TextView player_ind = null;
	private ImageView configure_button = null;
	private ImageView output_ind = null;
	private ImageView hs_battery_ind = null;

	@Override
	public void onAttach(Activity act) {
		super.onAttach(act);
		uiContext = (TLActivity) act;
		Log.i(TAG, "onAttach ....");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		selfId = getArguments().getInt("id.self");
		selfNick = getArguments().getString("nick.self");
		selfStatus = getArguments().getInt("state.self");
		Log.i(TAG, "onCreate with uid:" + selfId + " state:" + selfStatus);

		mAudioManager = ((AudioManager) uiContext.getSystemService(Context.AUDIO_SERVICE));
		talkHistory = new TalkHistory(selfId);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "onResume ....");

		uiHandler = TLActivity.getUiHandler();
		if (sessionApi == null) {
			uiHandler.postDelayed(delayInitApi, 300);
		}
		if (playlast_button != null) // crash catched button is null!
		{
			if (newLastSpeaking != null && !newLastSpeaking.played) {
				playlast_button.setSelected(true);
			} else {
				playlast_button.setSelected(false);
			}
			playlast_button.setActivated(false);

			output_ind.getDrawable().setLevel(getOutputDev());

			boolean bound = ((TLActivity) uiContext).isUserBoundPhone();
			if (bound) {
				// me_item.setTextColor(Color.rgb(232, 237, 35));
				Drawable dBound = uiContext.getResources().getDrawable(
						R.drawable.member_bound);
				me_item.setCompoundDrawablesWithIntrinsicBounds(dBound, null,
						null, null);
			}

			isUndistube = API.getAccountApi().isUndistubed();
			setUndistubeIcon(isUndistube);
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(TAG, "onStop ....");

		autoReleasePtt();
	}

	private Runnable delayInitApi = new Runnable() {
		@Override
		public void run() {
			if ((sessionApi = API.getSessionApi()) != null) {
				deviceApi = API.getDeviceApi();
				sessionApi.setOnMediaListener(TalkFragment.this);
				isUndistube = API.getAccountApi().isUndistubed();
				setUndistubeIcon(isUndistube);
			} else {
				uiHandler.postDelayed(delayInitApi, 300);
			}
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy ....");

		if (sessionApi != null) {
			sessionApi.setOnMediaListener(null);
			sessionApi = null;
		} else if (uiHandler != null) { // don't know why uiHandler can be null.
			uiHandler.removeCallbacks(delayInitApi);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outBu) {
		super.onSaveInstanceState(outBu);
		Log.i(TAG, "onSaveInstanceState ....");

		outBu.putString("StopByAndroid", "yes");
	}

	private interface PLS {
		public int LASTSPEAKING_NEW = 7;
		public int LASTSPEAKING_PLAYING = 8;
		public int LASTSPEAKING_END = 9;
		public int LASTSPEAKING_GONE = 10;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container != null) { // && ((TLActivity)uiContext).isHorizonScreen()
			View view = inflater.inflate(R.layout.fragment_talk, container,
					false);

			me_item = (TextView) view.findViewById(R.id.me_item);
			setUndistubeIcon(isUndistube);
			me_item.setText(selfNick != null ? selfNick : API.uid2nick(selfId));

			ptt_button = (ToggleButton) view.findViewById(R.id.ptt_button);
			if (selfStatus == Constant.CONTACT_STATE_ONLINE)
				ptt_button.setSelected(true);
			ptt_button.setOnTouchListener(this);

			configure_button = (ImageView) view
					.findViewById(R.id.configure_button);
			configure_button.setOnClickListener(this);

			ptt_mic_ind = (ImageView) view.findViewById(R.id.ptt_mic_ind);
			ptt_mic_ind.setSelected(true);
			ptt_mic_ind.setPressed(false);
			ptt_spk_ind = (ImageView) view.findViewById(R.id.ptt_spk_ind);
			ptt_spk_ind.setSelected(true);
			ptt_spk_ind.setPressed(false);

			level_ind = (ImageView) view.findViewById(R.id.level_ind);
			level_ind.getDrawable().setLevel(0);

			speaker_item = (TextView) view.findViewById(R.id.speaker_ind);
			speaker_item.setVisibility(View.INVISIBLE);
			queuer_item = (TextView) view.findViewById(R.id.queuer_ind);
			queuer_item.setVisibility(View.INVISIBLE);

			playlast_button = (ImageView) view
					.findViewById(R.id.playlast_button);
			playlast_button.setOnClickListener(this);
			playlast_button.setActivated(false);

			record_ind = (ImageView) view.findViewById(R.id.record_ind);
			record_ind.setVisibility(View.INVISIBLE);
			player_ind = (TextView) view.findViewById(R.id.player_ind);
			player_ind.setVisibility(View.INVISIBLE);

			initHisPlayers(view);

			output_ind = (ImageView) view.findViewById(R.id.output_select_button);
			output_ind.setLongClickable(true);
			output_ind.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View arg0) {
					if (uiHandler != null)
						uiHandler.sendEmptyMessage(MsgCode.MC_OUTPUTINDREQ);
					return false;
				}
			});
			hs_battery_ind = (ImageView) view.findViewById(R.id.handset_battery_level);
			hs_battery_ind.setVisibility(View.INVISIBLE);

			Log.i(TAG, "onCreateView OK ....");
			return view;
		} else {
			Log.i(TAG, "onCreateView IGNORE ....");
			return null;
		}

	}

	private void setUndistubeIcon(boolean on) {
		if (on) {
			Drawable dUndist = uiContext.getResources().getDrawable(
					R.drawable.member_undistube);
			me_item.setCompoundDrawablesWithIntrinsicBounds(dUndist, null,
					null, null);
		}
	}

	private List<TextView> hisPlys = new ArrayList<TextView>();

	private void initHisPlayers(View view) {
		hisPlys.add(0, (TextView) view.findViewById(R.id.player_his1));
		hisPlys.add(1, (TextView) view.findViewById(R.id.player_his2));
		hisPlys.add(2, (TextView) view.findViewById(R.id.player_his3));
		clearHisPlayers();
	}

	private void clearHisPlayers() {
		for (int i = 0; i < 3; i++) {
			hisPlys.get(i).setText("");
			hisPlys.get(i).setVisibility(View.INVISIBLE);
		}
	}

	private int getOutputDev() {
		if (((TLActivity) getActivity()).isBluetoothStarted())
			return OUT_BLUETOOTH_DISC;
		else
			return OUT_PHONEBODY;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG, "onDestroyView ....");
	}

	/*
	 * 
	 * MainActivity (TLActivity) 'SET' functions:
	 */

	public void startDialog(int self, int ctype, int sid, int[] ids) {
		if (sessionApi != null)
			sessionApi.startDialog(self, ctype, sid, ids);
	}

	public void stopDialog(int self, int dialog) {
		if (sessionApi != null)
			sessionApi.stopDialog(self, dialog);
	}

	public void onCurrentSessionChanged(int self, int ctype, int cid) {
		Log.i(TAG, "onCurrentSessionChanged " + ctype + ":" + cid);
		if (ctype != Constant.SESSION_TYPE_NONE) { // enter session
			currSession = new CompactID(ctype, cid);
			talkHistory.openFiles4Write(selfId, currSession.getCompactId());
		} else { // leave session
			currSession = null;
			newLastSpeaking = null;
			talkHistory.closeFiles();
		}
		uiTalkSessionChange();
	}

	public void onDisplaySessionChanged(int self, int ctype, int cid) {
		Log.i(TAG, "onDisplaySessionChanged " + ctype + ":" + cid);
		if (ctype != Constant.SESSION_TYPE_NONE) { // enter session
			dispSession = new CompactID(ctype, cid);
		} else { // leave session
			dispSession = null;
		}
		uiTalkSessionChange();
	}

	private void uiTalkSessionChange() {
		if (Channel.sameCid(currSession, dispSession)) {
			asyncSetUiItem(R.id.ptt_button, TRUE);
			flushPlaylastButton();
		} else {
			asyncSetUiItem(R.id.ptt_button, FALSE);
			asyncSetUiItem(R.id.playlast_button, PLS.LASTSPEAKING_END);
		}

		clearHisPlayers();
	}

	private void flushPlaylastButton() {
		if (newLastSpeaking != null && !newLastSpeaking.played) {
			asyncSetUiItem(R.id.playlast_button, PLS.LASTSPEAKING_NEW);
		} else {
			asyncSetUiItem(R.id.playlast_button, PLS.LASTSPEAKING_END);
		}
	}

	public void onSelfStatusChange(int state) {
		selfStatus = state;

		if (ptt_button == null)
			return;

		if (state == Constant.CONTACT_STATE_ONLINE)
			asyncSetUiItem(R.id.ptt_button, TRUE);
		else
			asyncSetUiItem(R.id.ptt_button, FALSE);
	}

	public void setSelfUndistubeInd(boolean undist) {
		boolean bound = ((TLActivity) uiContext).isUserBoundPhone();
		if (!undist) {
			if (bound)
				asyncSetUiItem(R.id.me_item, 3);
			else
				asyncSetUiItem(R.id.me_item, 2);
		} else {
			asyncSetUiItem(R.id.me_item, 1);
		}
	}

	private String selfNick = null;

	public void onSetNickName(String nick) {
		selfNick = nick;
		if (uiHandler != null) {
			uiHandler.post(new Runnable() {
				@Override
				public void run() {
					me_item.setText(selfNick);
				}
			});
		}
	}

	public void onBoundPhone() {
		((TLActivity) uiContext).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Drawable dBound = uiContext.getResources().getDrawable(
						R.drawable.member_bound);
				me_item.setCompoundDrawablesWithIntrinsicBounds(dBound, null,
						null, null);
			}
		});
	}

	public void setPttTrigglableOn(boolean on) {
		if (getOutputDev() == OUT_BLUETOOTH_DISC) {
			pttTriggerable = false;
		} else {
			if (!pttTriggerable && on) {
				pttTriggerable = true;
				if (mediaKeys == null) {
					mediaKeys = new ComponentName(uiContext, MediaKeys.class);
					mAudioManager.registerMediaButtonEventReceiver(mediaKeys);
					Log.i(TAG, "register Media Buttons Receiver.");
				}
			} else if (pttTriggerable && !on) {
				pttTriggerable = false;
				if (mediaKeys != null) {
					mAudioManager.unregisterMediaButtonEventReceiver(mediaKeys);
					mediaKeys = null;
				}
			}
		}

		autoReleasePtt();
	}

	private void autoReleasePtt() {
		if (isPttPressed && currSession != null) {
			talkRelease(currSession); // pttPressed <- false
		}
		isPttPressed = false;
	}

	public void setOutputDeviceInd(int lo) {
		asyncSetUiItem(R.id.output_select_button, lo);
		if (lo != OUT_BLUETOOTH_DISC && lo != OUT_BLUETOOTH_CONN) {
			asyncSetUiItem(R.id.handset_battery_level, 0);
		}
	}

	public boolean getPttTrigglable() {
		return pttTriggerable;
	}

	public int getAudioAmplitude() {
		if (deviceApi != null) {
			return deviceApi.getAudioAmpRate();
		}
		return 0;
	}

	public void setAudioAmplitude(int level) {
		if (deviceApi != null) {
			deviceApi.setAudioAmpRate(level);
		}
	}

	/*
	 * 
	 * OnMediaListener callbacks:
	 */

	@Override
	public void onPttButtonPressed(int uid, int state) {
		Log.i(TAG, "onPttButtonPressed state: " + state);
		if ((state & 0xff) == 0 || state == 0x8000) {
			asyncSetUiItem(R.id.ptt_button, TRUE);
		} else {
			asyncSetUiItem(R.id.ptt_button, FALSE);
			if (deviceApi != null) {
			//	deviceApi.setDefaultSpeakerOn(true);
			//	deviceApi.playNotifySound(Sound.PLAYER_MEDIA_ERROR);
			}
		}

		if (isMeInQueue && state == Constant.TALKREL_SUCCESSFUL) {
			isMeInQueue = false;
			asyncSetUiItem(R.id.queuer_ind, -1 * selfId);
		}
	}

	@Override
	public void onTalkRequestConfirm(int uid, int ctype, int sid, int tag, boolean enRed) {
		Log.i(TAG, "onTalkRequestConfirm " + ctype + ":" + sid + " " + tag);
		int[] ids = { R.id.ptt_mic_ind, R.id.record_ind };
		int[] sts = { enRed ? 2 : TRUE, TRUE };
		asyncSetUiItems(ids, sts);

		if (isMeInQueue) {
			isMeInQueue = false;
			asyncSetUiItem(R.id.queuer_ind, -1 * selfId);
		}
	}

	@Override
	public void onTalkRequestDeny(int uid, int ctype, int sid) {
		int[] ids = { R.id.ptt_button, R.id.record_ind };
		int[] sts = { FALSE, FALSE };
		asyncSetUiItems(ids, sts);
	}

	private boolean isMeInQueue = false;

	@Override
	public void onTalkRequestQueued(int uid, int ctype, int sid) {
		isMeInQueue = true;
		asyncSetUiItem(R.id.queuer_ind, selfId);
	}

	@Override
	public void onTalkReleaseConfirm(int arg0, int arg1) {
		Log.i(TAG, "onTalkReleaseConfirm " + arg0 + " " + arg1);
		int[] ids = { R.id.ptt_mic_ind, R.id.level_ind, R.id.record_ind };
		int[] sts = { FALSE, 0, FALSE };
		asyncSetUiItems(ids, sts);
	}

	@Override
	public void onMediaInitializedEnd(int userId, int channelType, int sessionId) {
		Toast.makeText(uiContext,
				channelType + ":" + sessionId + " media initialized.",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTalkTransmitBroken(int arg0, int arg1) {
		if (deviceApi != null) {
	//		deviceApi.makeVibrate(300);
		}
		asyncSetUiItem(R.id.ptt_button, FALSE);
	}

	@Override
	public void onMediaReceiverReport(long arg0, int arg1, int arg2, int arg3, int arg4) {
	}

	@Override
	public void onMediaSenderReport(long arg0, int arg1, int arg2, int arg3, int arg4) {
	}

	/*
	 * 
	 * 
	 */

	@Override
	public void onNewSpeakingCatched(final HistoryRecord hisRec) {
		if (hisRec.owner != selfId) {
			asyncSetUiItem(R.id.playlast_button, PLS.LASTSPEAKING_NEW);
			newLastSpeaking = hisRec;
			((TLActivity) uiContext).runOnUiThread(new Runnable(){
				@Override
				public void run() {
					if (popLastSpk != null) {
						popLastSpk.dismiss();
						popLastSpk = null;
					}
				}
			});
		}
		//
		((TLActivity) uiContext).runOnUiThread(new Runnable(){
			@Override
			public void run() {
				Log.i(TAG, "save new speech: stype:"+CompactID.getType(hisRec.session)+" sid:"+CompactID.getId(hisRec.session));
				talkHistory.dumpSpeechBuffer(hisRec.played, hisRec.session, hisRec.owner, hisRec.tag,
						hisRec.mediaData, hisRec.mediaData.length);
			}
		});
	}

	@Override
	public void onPlayLastSpeaking(int idx, int dur10ms) {
		Log.i(TAG, "onPlayLastSpeaking idx:" + idx + " dur:" + dur10ms);
		if (dur10ms > 0) {		// start successfully
			int[] ids = {R.id.playlast_button, R.id.player_ind};
			int[] sts = {PLS.LASTSPEAKING_PLAYING, idx};
			asyncSetUiItems(ids, sts);
			showSpeakingLevel = true;
		}
	}

	@Override
	public void onPlayLastSpeakingEnd(int speaker) {
		int[] ids = { R.id.playlast_button, R.id.player_ind, R.id.level_ind };
		int[] sts = { PLS.LASTSPEAKING_END, 0, 0 };
		asyncSetUiItems(ids, sts);
		showSpeakingLevel = false;

		if (lastPlaying >= 0 && popLastSpk != null) {
			recsData.get(lastPlaying).put(ISPLAYED, deacPlHd);
			plDatAdapter.notifyDataSetChanged();
			lastPlaying = -1;
		}
	}

	/*
	 * 
	 * 
	 * 
	 */

	private boolean delayShowSpeaking = false;
	private int delaySpeaker = 0;
	private boolean showSpeakingLevel = false;

	@Override
	public void onSomeoneSpeaking(int speaker, int ctype, int sessionId, int tag, int dur10ms) {
		Log.i(TAG, "speaker " + speaker + " tag " + tag + " dur " + dur10ms + " ptt:" + isPttPressed);
		if (isPttPressed && dur10ms < 200) {
			Log.i(TAG, "set delay show speaker " + speaker);
			delayShowSpeaking = true;
			delaySpeaker = speaker;
		} else {
			delayShowSpeaking = false;
			delaySpeaker = 0;
			asyncSetUiItem(R.id.speaker_ind, speaker);
		}
		asyncSetUiItem(R.id.queuer_ind, -1 * speaker);
		uiHandler.obtainMessage(MsgCode.MC_SESSIONACTIVE, ctype, sessionId).sendToTarget();
	}

	@Override
	public void onThatoneSayOver(int speaker, int tag) {
		delayShowSpeaking = false;
		delaySpeaker = 0;
		asyncSetUiItem(R.id.speaker_ind, 0);
	}

	@Override
	public void onStartPlaying(int speaker, int ctype, int session, int tag) {
		Log.i(TAG, "onStartPlaying " + speaker + " " + session + " " + tag);
		int[] ids = { R.id.ptt_spk_ind, R.id.player_ind };
		int[] sts = { TRUE, speaker };
		asyncSetUiItems(ids, sts);

		showSpeakingLevel = true;
		if (delayShowSpeaking) {
			asyncSetUiItem(R.id.speaker_ind, delaySpeaker);
			delayShowSpeaking = false;
			delaySpeaker = 0;
		}
	}

	@Override
	public void onPlayStopped(int tag) {
		Log.i(TAG, "onPlayStopped " + tag);
		int[] ids = { R.id.ptt_spk_ind, R.id.level_ind, R.id.player_ind };
		int[] sts = { FALSE, 0, 0 };
		asyncSetUiItems(ids, sts);
		showSpeakingLevel = false;
	}

	@Override
	public void onSomeoneAttempt(int userId, int ctype, int sessionId) {
		asyncSetUiItem(R.id.queuer_ind, userId);
	}

	@Override
	public void onThatAttemptQuit(int userId, int ctype, int sessionId) {
		asyncSetUiItem(R.id.queuer_ind, -1 * userId);
	}

	@Override
	public void onMediaSenderCutted(int userId, int tag) {
		((TLActivity) uiContext).runOnUiThread(new ReleaseTrigglePTT());
	}

	/*
	 * 
	 * 
	 */

	private int lastLevel = 0;

	@Override
	public void onPlayerMeter(int uid, int level) {
		if (showSpeakingLevel) {
			if (level != lastLevel)
				asyncSetUiItem(R.id.level_ind, level);
			lastLevel = level;
		} else
			asyncSetUiItem(R.id.level_ind, 0);
	}

	@Override
	public void onRecorderMeter(int uid, int level) {
		if (level != lastLevel)
			asyncSetUiItem(R.id.level_ind, level);
		lastLevel = level;
	}

	@Override
	public void onBluetoothBatteryGet(int level) {
		asyncSetUiItem(R.id.handset_battery_level, level + 1);
	}

	private boolean bluetoothConnected = false;
	@Override
	public void onBluetoothConnect(int status) {
		Log.i(TAG, "onBluetoothConnect "+status);
		bluetoothConnected = (status == Constant.ON);

		if (!((TLActivity) uiContext).isBluetoothStarted()) {
			setOutputDeviceInd(TalkFragment.OUT_PHONEBODY);
			bluetoothConnected = false;
			return;
		}

		// bluetoothStarted:
		if (status == Constant.ON) {
			setOutputDeviceInd(TalkFragment.OUT_BLUETOOTH_CONN);
		} else if (status == Constant.OFF) {
			setOutputDeviceInd(TalkFragment.OUT_BLUETOOTH_DISC);
		} else if (status == Constant.ERR) {
			setOutputDeviceInd(TalkFragment.OUT_BLUETOOTH_ERR);
		} else if (status == Constant.AERR) {
			setOutputDeviceInd(TalkFragment.OUT_BLUETOOTH_AERR);
		}
	}

	/*
	 * 
	 * UI functions:
	 */

	private boolean isPttTriggered = false;
	private volatile boolean isPttPressed = false;

	@Override
	public boolean onTouch(View _v, MotionEvent event) {
		int theAct = event.getAction();

		if (sessionApi == null || currSession == null) {
			if (isPttPressed) {
				isPttPressed = false;
			}
			if (theAct == MotionEvent.ACTION_UP) {
				Toast.makeText(uiContext, getResources().getString(R.string.no_session), Toast.LENGTH_SHORT).show();
			}
			return false;
		}

		if (!Channel.sameCid(currSession, dispSession)) {
			if (theAct == MotionEvent.ACTION_UP)
				uiHandler.obtainMessage(MsgCode.MC_SESSIONACTIVE,
						currSession.getType(), currSession.getId())
						.sendToTarget();
		} else {
			processPttAction(theAct);
		}

		return false;
	}

	public void processPttAction(int theAct) {
		if (!pttTriggerable) {
			if (theAct == MotionEvent.ACTION_DOWN) {
				// Log.i(TAG,
				// "ptt down. uid:"+selfId+" session "+sessionType+":"+sessionId);
				talkRequest(currSession);
			} else if (theAct == MotionEvent.ACTION_UP) {
				// Log.i(TAG, "ptt stop.");
				talkRelease(currSession);
			}
		} else { // ptt is trigger mode
			if (theAct == MotionEvent.ACTION_DOWN) {
				if (!isPttPressed) {
					isPttTriggered = true;
					talkRequest(currSession);
				}
			} else if (theAct == MotionEvent.ACTION_UP) {
				if (isPttPressed && !isPttTriggered) {
					talkRelease(currSession);
				}
				isPttTriggered = false;
			}
		}
	}

	private class ReleaseTrigglePTT implements Runnable {

		@Override
		public void run() {
			if (pttTriggerable && isPttPressed && !isPttTriggered) {
				talkRelease(currSession);
			}
		}

	}

	private void talkRequest(CompactID session) {
		isPttPressed = true;
		if (session != null && sessionApi != null)
			sessionApi.talkRequest(selfId, session.getType(), session.getId());
	}

	private void talkRelease(CompactID session) {
		isPttPressed = false;
		if (session != null && sessionApi != null)
			sessionApi.talkRelease(selfId, session.getType(), session.getId());
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.playlast_button) {
			if (playlast_button.isActivated()) { // is playing
				playlast_button.setActivated(false);
				if (sessionApi != null)
					sessionApi.stopPlayLastSpeaking(selfId);
			} else if (playlast_button.isSelected()) {
				if (Channel.sameCid(dispSession, currSession)) {
					if (sessionApi != null)
						sessionApi.playLastSpeaking(selfId, 0, newLastSpeaking.mediaData);
					if (newLastSpeaking != null && !newLastSpeaking.played)
						newLastSpeaking.played = true;
				} else {
					if (currSession != null)
						uiHandler.obtainMessage(MsgCode.MC_SESSIONACTIVE,
								currSession.getType(), currSession.getId())
								.sendToTarget();
				}
			} else {
				if (popLastSpk == null) {
					HistoryRecord[] hisRecs = null;
					if (sessionApi != null)
						hisRecs = talkHistory.getAllHistoryRecords(dispSession.getCompactId());

					if (hisRecs != null && hisRecs.length > 0)
						showHistoryRecords(hisRecs, dispSession.getType(),
								dispSession.getId());
					else {
						Log.e(TAG, "no hisRec found, disp stype:" + dispSession.getType() + " sid:" + dispSession.getId());
						Toast.makeText(uiContext, getResources().getString(R.string.no_history),
								Toast.LENGTH_SHORT).show();
					}
				} else {
					if (popLastSpk.isShowing())
						popLastSpk.dismiss();
					popLastSpk = null;
				}
			}

		} else if (id == R.id.configure_button) {
			Log.i(TAG, "configure button clicked.");
			PopupMenu popCfg = new PopupMenu(uiContext, v);
			MenuInflater menuInft = popCfg.getMenuInflater();
			menuInft.inflate(R.menu.main, popCfg.getMenu());
			popCfg.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					return ((TLActivity) getActivity())
							.onMainMenuItemClicked(item);
				}
			});

			((TLActivity) getActivity()).modiMenuStatus(popCfg.getMenu());
			popCfg.show();

		}
	}

	private static final String ISPLAYED = "lstspk_isplayed";
	private static final String ATTIME = "lstspk_attime";
	private static final String BYNAME = "lstspk_byname";
	private static final String DURATION = "lstspk_duration";
	private static final String dfltPlHd = ">";
	private static final String unPyPlHd = "*";
	private static final String deacPlHd = "-";
	private static final String actiPlHd = " ";
	private PopupWindow popLastSpk = null;
	private int lastPlaying = -1;
	private List<Map<String, Object>> recsData = null;
	private SimpleAdapter plDatAdapter = null;

	private void showHistoryRecords(HistoryRecord[] hisRecs, int stype, int sid) {
		final int lssType = stype;
		final int lssId = sid;
		recsData = new ArrayList<Map<String, Object>>();
		if (hisRecs != null && hisRecs.length > 0)
			for (HistoryRecord hisR : hisRecs) {
				Map<String, Object> disR = new HashMap<String, Object>();
				disR.put(ISPLAYED, hisR.played ? dfltPlHd : unPyPlHd);
				disR.put(ATTIME, tag2time(hisR.tag));
				disR.put(BYNAME, API.uid2nick(hisR.owner));
				disR.put(DURATION, dur2str(hisR.duration));
				recsData.add(disR);
			}

		plDatAdapter = new SimpleAdapter(uiContext, recsData,
				R.layout.last_speaking_item, new String[] { ISPLAYED, ATTIME,
						BYNAME, DURATION }, new int[] { R.id.is_played,
						R.id.at_time, R.id.by_name, R.id.duration });

		LayoutInflater inflater = (LayoutInflater) uiContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View viewPLRs = inflater.inflate(R.layout.last_speaking_list, null);
		ListView lv_pl_list = (ListView) viewPLRs
				.findViewById(R.id.last_speakings);
		lv_pl_list.setAdapter(plDatAdapter);
		lv_pl_list.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> lvPLs, View view,
					int position, long id) {
				if (sessionApi != null) {
					if (lastPlaying < 0) {
						lastPlaying = (int) id + 1;
						HistoryRecord hisRec = talkHistory.getHistoryRecord(new CompactID(lssType, lssId).getCompactId(), lastPlaying);
						if (hisRec != null) {
							hisRec.mediaData = new byte[hisRec.duration / 2 * AMR475_PL_SIZE];
							talkHistory.readSpeechBuffer(new CompactID(lssType, lssId).getCompactId(), lastPlaying, hisRec.mediaData, 0);
							sessionApi.playLastSpeaking(selfId, hisRec.owner, hisRec.mediaData);
							recsData.get((int) id).put(ISPLAYED, actiPlHd);
							plDatAdapter.notifyDataSetChanged();
						}
					} else if (lastPlaying == (int) id) {
						lastPlaying = -1;
						sessionApi.stopPlayLastSpeaking(selfId);
						recsData.get((int) id).put(ISPLAYED, deacPlHd);
						plDatAdapter.notifyDataSetChanged();
					} else {
						recsData.get(lastPlaying).put(ISPLAYED, deacPlHd);
						lastPlaying = -1;
						sessionApi.stopPlayLastSpeaking(selfId);
						//
						uiHandler.postDelayed(new DelayPlayLast((int) id,
								lssType, lssId), 50);
					}
				}
			}
		});
		lastPlaying = -1;

		popLastSpk = new PopupWindow(viewPLRs, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		popLastSpk.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.w256h128));
		popLastSpk.setFocusable(true);
		popLastSpk.setTouchable(true);
		popLastSpk.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				Log.i(TAG, "play last speaking popup window dismissed");
				popLastSpk = null;
				lastPlaying = -1;
				recsData = null;
				plDatAdapter = null;
			}
		});
		popLastSpk.showAsDropDown((View) playlast_button, 0, 5);
	}

	private class DelayPlayLast implements Runnable {
		private int idx = 0;
		private int stype = Constant.SESSION_TYPE_NONE;
		private int sid = 0;

		public DelayPlayLast(int i, int st, int si) {
			idx = i;
			stype = st;
			sid = si;
		}

		@Override
		public void run() {
			recsData.get(idx).put(ISPLAYED, actiPlHd);
			plDatAdapter.notifyDataSetChanged();
			lastPlaying = (int) idx + 1;
			HistoryRecord hisRec = talkHistory.getHistoryRecord(new CompactID(stype, sid).getCompactId(), lastPlaying);
			if (hisRec != null) {
				hisRec.mediaData = new byte[hisRec.duration / 2 * AMR475_PL_SIZE];
				talkHistory.readSpeechBuffer(new CompactID(stype, sid).getCompactId(), lastPlaying, hisRec.mediaData, 0);
				sessionApi.playLastSpeaking(selfId, hisRec.owner, hisRec.mediaData);
			}
		}
	}

	private String tag2time(long tag) {
		Date today = new Date();
		Date date = new Date(100L * tag + 1407300000000L);
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
				"MM-dd HH:mm:ss", Locale.US); // ("yyyy-MM-dd hh:mm:ss");
		String str = f.format(date);
		if (f.format(today).substring(0, 5).equals(str.substring(0, 5))) {
			return getResources().getString(R.string.today) + str.substring(5);
		} else {
			return str;
		}
	}

	private String dur2str(int d10ms) {
		double sec = (double) d10ms * 0.01;
		java.text.DecimalFormat df = new java.text.DecimalFormat("###.#");
		return df.format(sec);
	}

	private void asyncSetUiItem(int id, int state) {
		if (uiHandler != null)
			uiHandler.post(new UiItem(id, state));
	}

	private void asyncSetUiItems(int[] ids, int[] states) {
		if (uiHandler != null)
			uiHandler.post(new UiItem(ids, states));
	}

	private class UiItem implements Runnable {
		private int[] rids;
		private int[] newStates;

		public UiItem(int i, int s) {
			rids = new int[1];
			newStates = new int[1];
			rids[0] = i;
			newStates[0] = s;
		}

		public UiItem(int[] ids, int[] sts) {
			rids = ids;
			newStates = sts;
		}

		@Override
		public void run() {
			synchronized (TalkFragment.this) {
				for (int i = 0; i < rids.length; i++)
					doUpdateUI(rids[i], newStates[i]);
			}
		}

		private void doUpdateUI(int id, int state) {
			if (id == R.id.ptt_button) {
				ptt_button.setSelected(state == TRUE);
				ptt_mic_ind.setSelected(state == TRUE);
				ptt_spk_ind.setSelected(state == TRUE);

			} else if (id == R.id.ptt_mic_ind) {
				ptt_mic_ind.setPressed(state >= TRUE);
				if (state > TRUE)
					ptt_mic_ind.setColorFilter(0x44FF0000);
				else
					ptt_mic_ind.setColorFilter(null);

			} else if (id == R.id.ptt_spk_ind) {
				ptt_spk_ind.setPressed(state == TRUE);

			} else if (id == R.id.record_ind) {
				if (state == FALSE) {
					shiftHisPlayers("< me");
				}
				record_ind.setVisibility(state == TRUE ? View.VISIBLE
						: View.INVISIBLE);

			} else if (id == R.id.player_ind) {
				if (state == 0) {
					shiftHisPlayers("> " + player_ind.getText().toString());
				}
				player_ind.setVisibility(state == 0 ? View.INVISIBLE
						: View.VISIBLE);
				player_ind.setText(API.uid2nick(state));

			} else if (id == R.id.level_ind) {
				level_ind.getDrawable().setLevel(state);

			} else if (id == R.id.playlast_button) {
				if (state == PLS.LASTSPEAKING_GONE) {
					playlast_button.setVisibility(View.INVISIBLE);
				} else if (state == PLS.LASTSPEAKING_NEW) {
					playlast_button.setVisibility(View.VISIBLE);
					playlast_button.setSelected(true);
					playlast_button.setActivated(false);
				} else if (state == PLS.LASTSPEAKING_PLAYING) {
					playlast_button.setSelected(false);
					playlast_button.setActivated(true);
					ptt_spk_ind.setPressed(true);
				} else if (state == PLS.LASTSPEAKING_END) {
					playlast_button.setSelected(false);
					playlast_button.setActivated(false);
					ptt_spk_ind.setPressed(false);
				}

			} else if (id == R.id.speaker_ind) {
				if (state == 0) {
					speaker_item.setText("");
					speaker_item.setVisibility(View.INVISIBLE);
					// ptt_spk_ind.setPressed(false);
				} else {
					speaker_item.setText(API.uid2nick(state));
					speaker_item.setVisibility(View.VISIBLE);
					// ptt_spk_ind.setPressed(true);
				}

			} else if (id == R.id.queuer_ind) {// Log.d(TAG, "queuer_ind state: "+state);
				if (state == 0) {
					queuer_item.setText("");
					queuer_item.setVisibility(View.INVISIBLE);
				} else if (state > 0) {
					add2WaitList(state);
					queuer_item.setText(API.uid2nick(state));
					queuer_item.setVisibility(View.VISIBLE);
				} else {
					int iq = rmFromWaitQueue(-1 * state);
					// Log.d(TAG, "rmFromWaitQueue ret: "+iq);
					if (iq > 0) {
						queuer_item.setText(API.uid2nick(iq));
						queuer_item.setVisibility(View.VISIBLE);
					} else if (iq == 0) {
						queuer_item.setText("");
						queuer_item.setVisibility(View.INVISIBLE);
					}
				}

			} else if (id == R.id.output_select_button) {
				output_ind.getDrawable().setLevel(state);

			} else if (id == R.id.handset_battery_level) {
				if (state > 0) {
					// hs_battery_ind.setText("[ "+Integer.toString(25*(state-1))+" % ]");
					hs_battery_ind.getDrawable().setLevel(state - 1);
					hs_battery_ind.setVisibility(View.VISIBLE);
				} else {
					hs_battery_ind.setVisibility(View.INVISIBLE);
				}

			} else if (id == R.id.me_item) {
				Drawable dUndist = uiContext.getResources().getDrawable(
						R.drawable.member_normal);
				if (state == 1) {
					dUndist = uiContext.getResources().getDrawable(
							R.drawable.member_undistube);
				} else if (state == 3) {
					dUndist = uiContext.getResources().getDrawable(
							R.drawable.member_bound);
				}
				me_item.setCompoundDrawablesWithIntrinsicBounds(dUndist, null,
						null, null);

			}
		}

		private void add2WaitList(int uid) {
			if (waitList.contains(uid)) {
				waitList.remove((Object) uid);
			}
			int qsz = waitList.size();
			waitList.add(qsz, uid);
			return;
		}

		private int rmFromWaitQueue(int uid) {
			int qsz = waitList.size();

			if (qsz > 0) {
				for (int i = 0; i < qsz; i++) {
					int id = waitList.get(i);
					if (id == uid) {
						waitList.remove(i);
						if (qsz == 1) {
							return 0;
						} else if (i == qsz - 1) {
							return waitList.get(i - 1);
						} else {
							return -1;
						}
					}
				}
				return -1;
			}
			return 0;
		}

		private void shiftHisPlayers(String info) {
			if (hisPlys.get(2).getVisibility() == View.VISIBLE) {
				hisPlys.get(2).setText(hisPlys.get(1).getText().toString());
				hisPlys.get(1).setText(hisPlys.get(0).getText().toString());
				hisPlys.get(0).setText(info);
			} else if (hisPlys.get(1).getVisibility() == View.VISIBLE) {
				hisPlys.get(2).setVisibility(View.VISIBLE);
				hisPlys.get(2).setText(hisPlys.get(1).getText().toString());
				hisPlys.get(1).setText(hisPlys.get(0).getText().toString());
				hisPlys.get(0).setText(info);
			} else if (hisPlys.get(0).getVisibility() == View.VISIBLE) {
				hisPlys.get(1).setVisibility(View.VISIBLE);
				hisPlys.get(1).setText(hisPlys.get(0).getText().toString());
				hisPlys.get(0).setText(info);
			} else {
				hisPlys.get(0).setVisibility(View.VISIBLE);
				hisPlys.get(0).setText(info);
			}
		}
	}
}