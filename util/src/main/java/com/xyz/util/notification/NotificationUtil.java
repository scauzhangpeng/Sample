package com.xyz.util.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by ZP on 2017/12/19.
 * <p>
 * 通知栏工具类
 * </p>
 */

public class NotificationUtil {

    /**
     * 获取通知栏兼容管理类
     *
     * @param context {@link Context}
     * @return {@link NotificationManagerCompat}
     */
    public static NotificationManagerCompat getManager(Context context) {
        return NotificationManagerCompat.from(context);
    }

    /**
     * 显示通知
     *
     * @param context      {@link Context}
     * @param id           通知ID
     * @param notification {@link Notification}
     */
    public static void showNotify(Context context, int id, Notification notification) {
        getManager(context).notify(id, notification);
    }

    /**
     * 取消所有通知
     *
     * @param context {@link Context}
     */
    public static void cancelAll(Context context) {
        getManager(context).cancelAll();
    }


    public static class Builder extends NotificationCompat.Builder {

        /**
         * @param context
         * @inheritDoc
         */
        public Builder(Context context) {
            super(context);
        }

        /**
         * 设置大图标
         *
         * @param largeIcon 大图标ID
         */
        public Builder setLargeIcon(int largeIcon) {
            setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), largeIcon));
            return this;
        }

        /**
         * 设置震动
         */
        public Builder enableVibrate() {
            setVibrate(new long[]{0, 50, 100, 150});
            return this;
        }

        /**
         * 设置横幅显示
         *
         * @param pendingIntent {@link PendingIntent}
         */
        public Builder setFullScreenIntent(PendingIntent pendingIntent) {
            setFullScreenIntent(pendingIntent, true);
            return this;
        }

        /**
         * 设置列表，最多7条
         *
         * @param title 列表标题
         * @param list  列表内容
         */
        public Builder setInboxStyle(String title, String summaryText, ArrayList<String> list) {
            NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
            int size = list.size();
            if (size > 7) {
                size = 7;
            }
            for (int i = 0; i < size; i++) {
                style.addLine(list.get(i));
            }
            style.setBigContentTitle(title);
            if (!TextUtils.isEmpty(summaryText)) {
                style.setSummaryText(summaryText);
            }
            setStyle(style);
            return this;
        }

        /**
         * 设置列表，最多7条
         *
         * @param title 列表标题
         * @param list  列表内容
         */
        public Builder setInboxStyle(String title, ArrayList<String> list) {
            setInboxStyle(title, "", list);
            return this;
        }

        /**
         * 设置长文本显示样式
         *
         * @param normalContentTitle  未展开时标题
         * @param normalText          未展开时内容
         * @param bigText             展开时长文本内容
         * @param bigTextTitle        展开时标题
         * @param bigTextSummaryTitle 展开时概括标题
         */
        public Builder setBigTextStyle(String normalContentTitle, String normalText,
                                       String bigText, String bigTextTitle, String bigTextSummaryTitle) {
            setContentTitle(normalContentTitle);
            setContentText(normalText);
            android.support.v4.app.NotificationCompat.BigTextStyle style =
                    new android.support.v4.app.NotificationCompat.BigTextStyle();
            if (!TextUtils.isEmpty(bigTextTitle)) {
                style.setBigContentTitle(bigTextTitle);
            }

            if (!TextUtils.isEmpty(bigText)) {
                style.bigText(bigText);
            }
            if (!TextUtils.isEmpty(bigTextSummaryTitle)) {
                style.setSummaryText(bigTextSummaryTitle);
            }
            return this;
        }

        /**
         * 设置大图显示样式
         *
         * @param normalContentTitle 未展开时标题
         * @param normalText         未展开时内容
         * @param bigIcon            展开时图标
         * @param bigPicture         展开时大图片
         */
        public Builder setBigPictureStyle(String normalContentTitle, String normalText,
                                          String bigContentTitle, String bigTextSummary, int bigIcon, int bigPicture) {
            setContentTitle(normalContentTitle);
            setContentText(normalText);
            android.support.v4.app.NotificationCompat.BigPictureStyle style =
                    new android.support.v4.app.NotificationCompat.BigPictureStyle();
            if (bigIcon != -1) {
                style.bigLargeIcon(getBitmap(bigIcon));
            }
            if (bigPicture != -1) {
                style.bigPicture(getBitmap(bigPicture));
            }
            style.setBigContentTitle(bigContentTitle);
            style.setSummaryText(bigTextSummary);
            setStyle(style);
            return this;
        }

        /**
         * Override the large icon when the big notification is shown.
         */
        public Builder setBigPictureStyle(String normalContentTitle, String normalText,
                                          String bigContentText, String bigTextSummary, Bitmap bitmapIcon, Bitmap bitmapPicture) {
            setContentTitle(normalContentTitle);
            setContentText(normalText);
            android.support.v4.app.NotificationCompat.BigPictureStyle style =
                    new android.support.v4.app.NotificationCompat.BigPictureStyle();
            if (bitmapIcon != null) {
                style.bigLargeIcon(bitmapIcon);
            }
            if (bitmapPicture != null) {
                style.bigPicture(bitmapPicture);
            }
            style.setBigContentTitle(bigContentText);
            style.setSummaryText(bigTextSummary);
            setStyle(style);
            return this;
        }

        /**
         * Override the large icon when the big notification is shown.
         */
        public Builder setBigPictureStyle(String normalContentTitle, String normalText,
                                          String bigContentTitle, String bigTextSummary, int bigPicture) {
            setBigPictureStyle(normalContentTitle, normalText, bigContentTitle, bigTextSummary, -1, bigPicture);
            return this;
        }

        private Bitmap getBitmap(int drawableId) {
            return BitmapFactory.decodeResource(mContext.getResources(), drawableId);
        }

        /**
         * 设置进度条
         *
         * @param currentProgress 当前进度
         */
        public Builder updateProgress(final int currentProgress) {
            executeOnFileIoThread(new Runnable() {
                @Override
                public void run() {
                    setProgress(100, currentProgress, false);
                }
            });
            return this;
        }

        /**
         * 设置模糊进度条
         */
        public Builder updateProgress() {
            executeOnFileIoThread(new Runnable() {
                @Override
                public void run() {
                    setProgress(100, 0, true);
                }
            });
            return this;
        }

        /**
         * 设置声音
         *
         * @param soundResource 媒体资源
         */
        public Builder setSound(int soundResource) {
            Uri uri = Uri.parse("android.resource://" + mContext.getPackageName()
                    + "/" + soundResource);
            setSound(uri);
            return this;
        }

        /**
         * 设置PendingIntent后默认清除
         *
         * @param intent {@link PendingIntent}
         */

        public Builder setContentIntent(PendingIntent intent) {
            setAutoCancel(true);
            super.setContentIntent(intent);
            return this;
        }

        /**
         * 交互式回复通知栏
         *
         * @param icon          图标
         * @param title         标题
         * @param pendingIntent {@link PendingIntent}
         * @param replyKey      内容的键，用于后续获取值
         * @param hint          提示信息
         */
        public Builder addAction(int icon, String title, PendingIntent pendingIntent,
                                 String replyKey, String hint) {
            RemoteInput remoteInput = getRemoteInput(replyKey, hint);
            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder(icon, title, pendingIntent)
                            .addRemoteInput(remoteInput)
                            .build();
            addAction(action);
            return this;
        }
    }

    private static void executeOnFileIoThread(Runnable runnable) {
        fileIoExecutor.execute(runnable);
    }

    // these lines are originally copied from LeakCanary: Copyright (C) 2015 Square, Inc.
    private static final Executor fileIoExecutor = newSingleThreadExecutor("File-IO");

    private static Executor newSingleThreadExecutor(String threadName) {
        return Executors.newSingleThreadExecutor(new SingleThreadFactory(threadName));
    }


    public static PendingIntent getNormalPending(Context context, Class<?> cls) {
        Intent resultIntent = new Intent(context, cls);
        return PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * 保存返回栈
     *
     * @param context {@link Context}
     * @param cls     启动的组件 配置Activity的parentActivityName属性
     * @return {@link PendingIntent}
     */
    public static PendingIntent getGoBackPending(Context context, Class<?> cls) {
        Intent resultIntent = new Intent(context, cls);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // 添加返回栈
        stackBuilder.addParentStack(cls);
        // 添加Intent到栈顶
        stackBuilder.addNextIntent(resultIntent);
        // 创建包含返回栈的pendingIntent
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static RemoteInput getRemoteInput(String replyKey, String label) {
        return new RemoteInput.Builder(replyKey)
                .setLabel(label)
                .build();
    }
}
