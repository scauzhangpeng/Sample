/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xyz.sample.notification;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.core.app.NotificationCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;

import com.xyz.sample.R;
import com.xyz.util.notification.NotificationUtil.Builder;

import java.util.ArrayList;

/**
 * Collection of notification builder presets.
 */
public class NotificationPresets {
    private static final String EXAMPLE_GROUP_KEY = "example";

    public static final NotificationPreset BASIC = new BasicNotificationPreset();
    public static final NotificationPreset STYLIZED_TEXT = new StylizedTextNotificationPreset();
    public static final NotificationPreset INBOX = new InboxNotificationPreset();
    public static final NotificationPreset BIG_PICTURE = new BigPictureNotificationPreset();
    public static final NotificationPreset BIG_TEXT = new BigTextNotificationPreset();


    public static final NotificationPreset[] PRESETS = new NotificationPreset[]{
            BASIC,
            STYLIZED_TEXT,
            INBOX,
            BIG_PICTURE,
            BIG_TEXT,
    };

    private static Builder applyBasicOptions(Context context,
                                             Builder builder,
                                             NotificationPreset.BuildOptions options) {
        builder.setContentTitle(options.titlePreset)
                .setContentText(options.textPreset)
                .setSmallIcon(R.mipmap.ic_notification);
        if (!TextUtils.isEmpty(options.subText)) {
            builder.setSubText(options.subText);
        }
        if (!TextUtils.isEmpty(options.ticker)) {
            builder.setTicker(options.ticker);
        }
        options.priorityPreset.apply(builder);
        if (options.includeLargeIcon) {
            builder.setLargeIcon(R.drawable.example_large_icon);
        }
        if (options.isLocalOnly) {
            builder.setLocalOnly(true);
        }
        if (options.defaultAll) {
            builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        }
        if (options.vibrate) {
            builder.setVibrate(new long[]{0, 100, 50, 100});
        }
        if (options.customSound) {
            builder.setSound(R.raw.notification);
        }
        return builder;
    }

    private static class BasicNotificationPreset extends NotificationPreset {
        public BasicNotificationPreset() {
            super(R.string.basic_example, R.string.example_content_title,
                    R.string.example_content_text);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            Builder builder = new Builder(context);
            applyBasicOptions(context, builder, options);
            return new Notification[]{builder.build()};
        }
    }

    private static class StylizedTextNotificationPreset extends NotificationPreset {
        public StylizedTextNotificationPreset() {
            super(R.string.stylized_text_example, R.string.example_content_title,
                    R.string.example_content_text);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();

            SpannableStringBuilder title = new SpannableStringBuilder();
            appendStyled(title, "Stylized", new StyleSpan(Typeface.BOLD_ITALIC));
            title.append(" title");
            SpannableStringBuilder text = new SpannableStringBuilder("Stylized text: ");
            appendStyled(text, "C", new ForegroundColorSpan(Color.RED));
            appendStyled(text, "O", new ForegroundColorSpan(Color.GREEN));
            appendStyled(text, "L", new ForegroundColorSpan(Color.BLUE));
            appendStyled(text, "O", new ForegroundColorSpan(Color.YELLOW));
            appendStyled(text, "R", new ForegroundColorSpan(Color.MAGENTA));
            appendStyled(text, "S", new ForegroundColorSpan(Color.CYAN));
            text.append("; ");
            appendStyled(text, "1.25x size", new RelativeSizeSpan(1.25f));
            text.append("; ");
            appendStyled(text, "0.75x size", new RelativeSizeSpan(0.75f));
            text.append("; ");
            appendStyled(text, "underline", new UnderlineSpan());
            text.append("; ");
            appendStyled(text, "strikethrough", new StrikethroughSpan());
            text.append("; ");
            appendStyled(text, "bold", new StyleSpan(Typeface.BOLD));
            text.append("; ");
            appendStyled(text, "italic", new StyleSpan(Typeface.ITALIC));
            text.append("; ");
            appendStyled(text, "sans-serif-thin", new TypefaceSpan("sans-serif-thin"));
            text.append("; ");
            appendStyled(text, "monospace", new TypefaceSpan("monospace"));
            text.append("; ");
            appendStyled(text, "sub", new SubscriptSpan());
            text.append("script");
            appendStyled(text, "super", new SuperscriptSpan());

            style.setBigContentTitle(title);
            style.bigText(text);

            NotificationCompat.Builder builder = new Builder(context)
                    .setStyle(style);
            applyBasicOptions(context, (Builder) builder, options);
            return new Notification[]{builder.build()};
        }

        private void appendStyled(SpannableStringBuilder builder, String str, Object... spans) {
            builder.append(str);
            for (Object span : spans) {
                builder.setSpan(span, builder.length() - str.length(), builder.length(), 0);
            }
        }
    }

    private static class InboxNotificationPreset extends NotificationPreset {
        public InboxNotificationPreset() {
            super(R.string.inbox_example, R.string.example_content_title,
                    R.string.example_content_text);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            Builder builder = new Builder(context);
            ArrayList<String> data = new ArrayList<>();
            data.add(context.getString(R.string.inbox_style_example_line1));
            data.add(context.getString(R.string.inbox_style_example_line2));
            data.add(context.getString(R.string.inbox_style_example_line3));
            data.add(context.getString(R.string.inbox_style_example_line1));
            data.add(context.getString(R.string.inbox_style_example_line2));
            data.add(context.getString(R.string.inbox_style_example_line3));
            data.add(context.getString(R.string.inbox_style_example_line1));
            data.add(context.getString(R.string.inbox_style_example_line2));
            data.add(context.getString(R.string.inbox_style_example_line3));
            builder.setInboxStyle(context.getString(R.string.inbox_style_example_title),
                    context.getString(R.string.inbox_style_example_summary_text), data);
            applyBasicOptions(context, builder, options);
            return new Notification[]{builder.build()};
        }
    }

    private static class BigPictureNotificationPreset extends NotificationPreset {
        public BigPictureNotificationPreset() {
            super(R.string.big_picture_example, R.string.example_content_title,
                    R.string.example_content_text);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            Builder builder = new Builder(context);
            String bigContentTitle = context.getString(R.string.big_picture_style_example_title);
            String summaryText = context.getString(
                    R.string.big_picture_style_example_summary_text);
            builder.setBigPictureStyle("", "", bigContentTitle, summaryText, R.drawable.example_big_picture,
                    R.drawable.example_big_picture);
            applyBasicOptions(context, builder, options);
            return new Notification[]{builder.build()};
        }
    }

    private static class BigTextNotificationPreset extends NotificationPreset {
        public BigTextNotificationPreset() {
            super(R.string.big_text_example, R.string.example_content_title,
                    R.string.example_content_text);
        }

        @Override
        public Notification[] buildNotifications(Context context, BuildOptions options) {
            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
            style.bigText(context.getString(R.string.big_text_example_big_text));
            style.setBigContentTitle(context.getString(R.string.big_text_example_title));
            style.setSummaryText(context.getString(R.string.big_text_example_summary_text));

            NotificationCompat.Builder builder = new Builder(context)
                    .setStyle(style);
            applyBasicOptions(context, (Builder) builder, options);
            return new Notification[]{builder.build()};
        }
    }
}
