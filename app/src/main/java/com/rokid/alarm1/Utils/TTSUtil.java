package com.rokid.alarm1.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import rokid.content.RokidContext;
import rokid.os.ITTSCallback;
import rokid.service.interfaces.TTSInterface;


public class TTSUtil {
    private static RokidContext mCtx;
    private static TTSInterface mTtsProxy;

    private static TtsCallBack callBack;

    public static void setTtsCallback(TtsCallBack callback) {
        TTSUtil.callBack = callback;
    }

    public static void showTTS(Context context, String content) {
        boolean callTtsState = true;
        if (mCtx == null) {
            mCtx = RokidContext.getInstance();
        }
        if (mCtx == null) {
            callTtsState = false;
        } else {
            if (mTtsProxy == null) {
                mTtsProxy = (TTSInterface) mCtx.getSystemRemoteService(context, RokidContext
                        .TTS_SERVICE);
            } else {
                try {
                    mTtsProxy.unregisterTTSCallback(context.getApplicationContext().getPackageName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (mTtsProxy.isSpeaking()) {
                        mTtsProxy.stop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (mTtsProxy == null) {
                callTtsState = false;
            } else {
                try {
                    mTtsProxy.registerTTSDeadListener(new TTSInterface.onTTSDeathListener() {
                        @Override
                        public void onTTSServerDead() {
                            // TODO handle exception
                        }
                    });
                    callTtsState = mTtsProxy.registerTTSCallback(context.getApplicationContext().getPackageName(),
                            mTtsCbk);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    callTtsState = false;
                }
            }
        }
        if (callTtsState) {
            try {
                mTtsProxy.speakIt(content);
            } catch (RemoteException e) {
                e.printStackTrace();
                if (callBack != null) {
                    callBack.ttsCallBack(false, -1, content);
                }
            }
        } else {
            if (callBack != null) {
                callBack.ttsCallBack(false, -1, content);
            }
        }
    }

    public static void stopSpk(Context context) {
        if (mTtsProxy != null) {
            try {
                mTtsProxy.unregisterTTSCallback(context.getApplicationContext().getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (mTtsProxy.isSpeaking()) {
                    mTtsProxy.stop();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (callBack != null) {
                callBack.ttsCallBack(true, -1, msg.obj.toString());
            }
        }
    };
    private static final ITTSCallback.Stub mTtsCbk = new ITTSCallback.Stub() {
        @Override
        public int ttsEvent(int what, String s) throws RemoteException {
            try {
                mTtsProxy.unregisterTTSCallback(s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            switch (what) {
                case 1 << 0:
                    message.what = what;
                    message.obj = s;
                    handler.sendMessage(message);
                    break;
                case 1 << 7:
                    message.what = what;
                    message.obj = s;
                    handler.sendMessage(message);
                    break;
                default:
                    message.what = what;
                    message.obj = s;
                    handler.sendMessage(message);
            }
            return 0;
        }
    };


    public interface TtsCallBack {
        /**
         * tts调用回调，不需要管state以及what，只要回调在当前的系统架构下即代表tts调用已经结束
         *
         * @param state 状态，代表是否调用tts成功
         * @param what  tts回调类型
         * @param s     call 那么
         */
        void ttsCallBack(boolean state, int what, String s);

    }
}
