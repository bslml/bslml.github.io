
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Jiami {
    private static String jsonResult = "";
    private static final String jsonMode = "{\n" +
            "\t\"Name\": \"#_NAME\",\n" +
            "\t\"Description\": \"#_DESCREPTION\",\n" +
            "\t\"Hint\": \"#_HINT\",\n" +
            "\t\"IsDescypted\": true,\n" +
            "\t\"IsVipOnly\": #_VIP,\n" +
            "\t\"InnerName\": \"#_FILE_NAME\",\n" +
            "\t\"Key\": \"#_CIPHER_KEY\",\t\n" +
            "\t\"FileURL\": \"https://iphunter.coding.me/#_DAY_STR/#_FILE_NAME\"\n" +
            "}";
    private static String key = "+{njyV0f^RA>6[sh";
    private static String cipherKey = "";
    private static boolean isVip = false;
    private static String dayStr = "mode";
    private static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void WriteStringToFile(String filePath, String content) {
            try {
                BufferedWriter fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
                fos.write(content);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
   
  
  
  
  
      public static void main(String[] args)
    {
        File directory = new File("");//设定为当前文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            ArrayList<File> fileList = getListFiles("./");
            for(File file : fileList)
            {
                if(file.getAbsolutePath().endsWith(".db"))
                {
                    String fileNameDB = file.getName();
                    //复制评论html
                    String commentFilePath = directory.getAbsolutePath() + "/" + fileNameDB + ".html";
                    Path originalDirectory = FileSystems.getDefault().getPath(directory.getAbsolutePath() + "/origin.html");
                    Path newDirectory = FileSystems.getDefault().getPath(commentFilePath);
                    Files.copy(originalDirectory,newDirectory);
                }

            }
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Hello world!");
    }
  

    private static void copyFileUsingJava7Files(File source, File dest)
            throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }

    public static ArrayList<File> getListFiles(Object obj) {
        File directory = null;
        if (obj instanceof File) {
            directory = (File) obj;
        } else {
            directory = new File(obj.toString());
        }
        ArrayList<File> files = new ArrayList<File>();
        if (directory.isFile()) {
            files.add(directory);
            return files;
        } else if (directory.isDirectory()) {
            File[] fileArr = directory.listFiles();
            if(fileArr != null) {
                files.addAll(Arrays.asList(fileArr));
            }
        }
        return files;
    }
}






/**
 * 工具类
 *
 * 对okHttp3 发送的josn数据加密 返回的json数据解密
 *
 */
