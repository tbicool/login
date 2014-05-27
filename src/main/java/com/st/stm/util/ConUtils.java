package com.st.stm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.st.stm.service.ServiceException;
import com.st.stm.service.ShiroDbRealm.ShiroUser;

/**
 * 常用工具类
 * 
 * @author tbicool
 * 
 */
public class ConUtils {
	private static SimpleDateFormat dateformater;
	public static final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	/**
	 * 获取用户登录信息
	 * 
	 * @return
	 */
	public static ShiroUser getUser() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		ShiroUser shiro = (ShiroUser) session.getAttribute("login");
		return shiro;
	}

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	public static Date getDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 获取系统时间唯一值
	 * 
	 * @return
	 */
	public static String formatseq() {
		return sft.format(System.currentTimeMillis());
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String fr) {
		SimpleDateFormat format = new SimpleDateFormat(fr);
		String dateString = format.format(date);
		return dateString;
	}

	/**
	 * 根据字符串转换成日期格式
	 * 
	 * @param strDate 日期格式字符串
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date parseDate(String strDate) {
		java.util.Date date = null;
		try {
			date = getDateFormater().parse(strDate);
		} catch (Exception ex) {
			// System.err.println(ex.getMessage());
		}
		return date;
	}

	/**
	 * 获取日期格式的DateFormater
	 * 
	 * @return DateFormat
	 */
	private static DateFormat getDateFormater() {
		if (dateformater == null) {
			dateformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return dateformater;
	}

	/**
	 * 将java.util.Date格式转换成日期字符串
	 * 
	 * @return String
	 */
	public static String formatDate(java.util.Date date) {
		return getDateFormater().format(date);
	}

	/**
	 * 字符串转换时间格式
	 * 
	 * @param str
	 * @param fr
	 * @return
	 */
	public static Date revDate(String strDate, String parse) {
		SimpleDateFormat format = new SimpleDateFormat(parse);
		try {
			Date date = format.parse(strDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 校验非空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullString(Object obj) {
		if (obj != null) {
			String str = String.valueOf(obj);
			if (str.length() > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验长度
	 * 
	 * @param obj
	 * @param len
	 * @return
	 */
	public static boolean isInLen(Object obj, int len) {
		if (obj != null) {
			String str = String.valueOf(obj);
			if (str.length() > len) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验非空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj != null) {
			String str = String.valueOf(obj);
			if (str.length() > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验是否是字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNumber(Object obj) {

		if (obj == null) {
			return false;
		}
		String szNum = String.valueOf(obj);
		if (isNull(szNum)) {
			return false;
		}
		char[] str = szNum.toCharArray();
		for (int i = 0; i <= (str.length - 1); i++) {
			char chr = str[i];
			if ((i == 0) && (chr == '-')) {
				continue;
			} else if (chr == '.') {
				continue;
			} else if ((chr > '9') || (chr < '0')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 通过流生成文件到指定目录
	 * 
	 */
	public static void createFileForInputStream(String fileDirStr, String fileName, InputStream inputStream) {
		FileOutputStream fos;
		File fileDir = new File(fileDirStr);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			fos = new FileOutputStream(fileDirStr + fileName);
			byte[] buffer = new byte[8192]; // 每次读8K字节
			int count = 0;
			// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
			while ((count = inputStream.read(buffer)) > 0) {
				fos.write(buffer, 0, count); // 向服务端文件写入字节流
			}
			fos.close(); // 关闭FileOutputStream对象
			inputStream.close(); // InputStream对象
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 将字符串生成文件到指定目录
	 * 
	 * @param fileDirStr 文件路径
	 * @param fileName 文件名字
	 * @param content 文件内容
	 */
	public static void createFileGB2312InputStream(String fileDirStr, String fileName, String content) {
		FileOutputStream fos;
		File fileDir = new File(fileDirStr);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			fos = new FileOutputStream(fileDirStr + fileName);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "GBK"));
			writer.write(content);
			writer.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param fileDirStr
	 *            下级子目录
	 * @param fileName
	 *            文件名
	 * @param result
	 *            内容
	 */
	public static void createFileToDir(String fileDirStr, String fileName, List result) {
		boolean isIOException = false;
		String filePath = fileDirStr + fileName;
		File fileDir = new File(fileDirStr);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File file = new File(filePath);
		FileOutputStream out = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
			if ((result != null) && (result.size() > 0)) {
				for (int i = 0; i < result.size(); i++) {
					bw.write(result.get(i).toString());
					if (i != (result.size() - 1)) {
						bw.newLine();
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isIOException = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isIOException = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isIOException = true;
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isIOException = true;
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isIOException = true;
				}

			}
			if (isIOException) {// 发生IO异常时
				file.delete();
			}
		}
	}

	public static void createFile(String record, BufferedWriter bw, File file) {

		try {
			bw.write(record);
			bw.newLine();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 刪除文件
	 * 
	 * @param path 文件路径
	 */
	public static void deleteFile(File file) {
		if (file != null) {
			if (file.isFile() || (file.list().length == 0)) {
				file.delete();
			} else {
				File[] files = file.listFiles();
				for (File f : files) {
					deleteFile(f);
				}
			}
			file.delete();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param downPath 下载文件路径
	 * @param response
	 */
	public static void downloadFile(String downPath, HttpServletResponse response) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(downPath));
			long fileLength = new File(downPath).length();
			String fileName = downPath.substring(downPath.lastIndexOf(File.separator) + 1, downPath.length());
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + "\"");
			response.addHeader("Content-Length", "" + String.valueOf(fileLength));
			response.setContentType("application/octet-stream;charset=UTF-8");
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("下载的文件没有找到");
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while ((offset < bytes.length) && ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		is.close();
		return bytes;
	}

	/**
	 * 判断是否手机号
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isPhoneNo(String number) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|147)\\d{8}$");
		Matcher m = p.matcher(number);
		return m.find();
	}

	/**
	 * 判断移动的手机号
	 * 
	 * @param true 是，false 否
	 * @return
	 */
	public static boolean isMobileNo(String number) {
		Pattern p = Pattern.compile("^(13[4-9]|15(0|1|2|4|7|8|9)|18(2|7|8)|147)\\d{8}$");
		Matcher m = p.matcher(number);
		return m.find();// boolean
	}

	/**
	 * 判断联通的手机号
	 * 
	 * @param true 是，false 否
	 * @return
	 */
	public static boolean isUnicomNo(String number) {
		Pattern p = Pattern.compile("^(13[0-2]|15(5|6)|18(5|6))\\d{8}$");
		Matcher m = p.matcher(number);
		return m.find();// boolean
	}

	/**
	 * 判断电信的手机号
	 * 
	 * @param true 是，false 否
	 * @return
	 */
	public static boolean isTelecomNo(String number) {
		Pattern p = Pattern.compile("^(133|153|18(0|9))\\d{8}$");
		Matcher m = p.matcher(number);
		return m.find();// boolean
	}

	public static void main(String[] args) {
		boolean mobileNo = ConUtils.isMobileNo("13110545608");
		System.out.println(mobileNo);
	}
}
