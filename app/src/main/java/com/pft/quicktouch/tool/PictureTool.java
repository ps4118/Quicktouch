package com.pft.quicktouch.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * 图片、拍照工具
 */
public class PictureTool {
    /**
     * 用来请求照相功能的常量
     */
    public static final int CAMERA_WITH_DATA = 168;
    /**
     * 用来请求图片选择器的常量
     */
    public static final int PHOTO_PICKED_WITH_DATA = 169;
    /**
     * 用来请求图片裁剪的
     */
    public static final int PHOTO_CROP = 170;
    /**
     * 拍照的照片存储位置及App图片保存位置
     */
    public static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/QuickTouch/");

    private static File mCurrentPhotoFile;// 照相机拍照得到的图片

    public File file; // 截图后得到的图片
    public static Uri imageUri; // 拍照后的图片路径
    public static Uri imageCaiUri; // 裁剪后的图片路径

    /**
     * 图片压缩处理，size参数为压缩比，比如size为2，则压缩为1/4
     **/
    public static Bitmap compressBitmap(String path, byte[] data,
                                        Context context, Uri uri, int size, boolean width) {
        Options options = null;
        Bitmap bm = null;
        if (size > 0) {
            Options info = new Options();
            /** 如果设置true的时候，decode时候Bitmap返回的为数据将空 */
            info.inJustDecodeBounds = true;
            info.inPurgeable = true;
            info.inPreferredConfig = Bitmap.Config.RGB_565;
            bm = decodeBitmap(path, data, context, uri, info);
            int dim = info.outWidth;
            if (!width)
                dim = Math.max(dim, info.outHeight);
            info = new Options();
            /** 把图片宽高读取放在Options里 */
            info.inSampleSize = size;
            info.inJustDecodeBounds = false;
            try {
                bm = decodeBitmap(path, data, context, uri, info);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bm;
    }

    /**
     * 把byte数据解析成图片
     */
    public static Bitmap decodeBitmap(String path, byte[] data,
                                      Context context, Uri uri, Options options) {
        Bitmap result = null;
        if (path != null) {
            result = BitmapFactory.decodeFile(path, options);
        } else if (data != null) {
            result = BitmapFactory.decodeByteArray(data, 0, data.length,
                    options);
        } else if (uri != null) {
            InputStream inputStream = null;
            try {
                ContentResolver cr = context.getContentResolver();
                inputStream = cr.openInputStream(uri);
                // result = BitmapFactory.decodeFile(path, options);
                result = BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();
                if (result != null && !result.isRecycled()) {
                    // result.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void doTakePhoto(Context mContext) {
        try {
            if (!PHOTO_DIR.exists()) {
                PHOTO_DIR.mkdirs();// 创建照片的存储目录
            }
            imageUri = Uri.fromFile(getmCurrentPhotoFile());
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            ((Activity) mContext).startActivityForResult(intent,
                    CAMERA_WITH_DATA);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext, "没有找到照相机", Toast.LENGTH_SHORT).show();
        }
    }

    public static void saveMyBitmap(Bitmap mBitmap, File file) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bitmapData = baos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            baos.close();
            bitmapData = null;
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 压缩图片(第二次)
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于150kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10; // 每次都减少10
            image.compress(Bitmap.CompressFormat.PNG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            if (options == 90) {
                break;
            }
        }
        if (image != null && !image.isRecycled()) {
            // image.recycle();
            image = null;
            // System.gc();
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        try {
            isBm.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }

    // 压缩图片(第一次)
    public static Bitmap comp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 200) {// 判断如果图片大于200KB,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.PNG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Options newOpts = new Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        try {
            isBm.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        return bitmap;
    }

    /**
     * 得到当前图片文件的路径
     *
     * @return
     */
    public static File getmCurrentPhotoFile() {
        if (mCurrentPhotoFile == null) {
            if (!PHOTO_DIR.exists()) {
                PHOTO_DIR.mkdirs(); // 创建照片的存储目录
            }
            mCurrentPhotoFile = new File(PHOTO_DIR, getCharacterAndNumber()
                    + ".png"/* 此处可更换文件后缀 */);
            if (!mCurrentPhotoFile.exists()) // 判断存储文件是否存在>不存在则创建
                try {
                    mCurrentPhotoFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return mCurrentPhotoFile;
    }

    /**
     * 调用相册程序
     *
     * @param context
     * @param iscrop
     */
    public static void doPickPhotoFromGallery(Context context) {
        try {
            if (!PHOTO_DIR.exists()) {
                PHOTO_DIR.mkdirs();// 创建照片的存储目录
            }
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            // "image/*");
            ((Activity) context).startActivityForResult(intent,
                    PHOTO_PICKED_WITH_DATA);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "没有找到相册", 0).show();
        }
    }

    /**
     * 裁剪
     *
     * @param uri
     */
    public static void crop(Activity context, Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "PNG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, PictureTool.PHOTO_CROP);
    }

    public static Uri getImageUri(File file) {
        // File temporaryFile = new File(PHOTO_DIR, getCharacterAndNumber()
        // + ".png");
        // imageUri = Uri.fromFile(file);
        return imageUri;
    }

    public static Uri getImageCaiUri() {
        File temporaryFile = new File(PHOTO_DIR, getCharacterAndNumber()
                + ".png");
        imageCaiUri = Uri.fromFile(temporaryFile);
        return imageCaiUri;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCharacterAndNumber() {
        String rel = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        rel = formatter.format(curDate);
        return rel;
    }

}
