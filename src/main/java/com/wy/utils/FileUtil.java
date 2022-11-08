package com.wy.utils;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 作者 wangyang <br/>
 * 创建时间 2022/9/18 <br/>
 * 描述 <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;流工具类 <br/>
 */
public class FileUtil {

	/**
	 * 描述: 读取一个文件的全部信息，以每一行为单位，存放到list集合中 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: input-目标文件的字节输入流 <br/>
	 * 返回值: 包含每行内容的List<String>集合 <br/>
	 */
	public static List<String> loadFile(InputStream input) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(input));
		List<String> result=new ArrayList<String>();
		String buffer;
		while( (buffer=br.readLine())!=null ){
			result.add(buffer);
		}
		return result;
	}

	/**
	 * 描述: 将多个文件写入一个压缩文件的方法 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/11/8 <br/>
	 * 参数: srcFiles-需要压缩的文件数组,zipFile-压缩结果文件路径,byteLeng--默认1024写入文件时的数组大小 <br/>
	 * 返回值:  <br/>
	 */
	public static void zipFiles(File[] srcFiles, File zipFile,int byteLeng) {
		// 判断压缩后的文件存在不，不存在则创建
		if (!zipFile.exists()) {
			try {
				zipFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 创建 FileOutputStream 对象
		FileOutputStream fileOutputStream = null;
		// 创建 ZipOutputStream
		ZipOutputStream zipOutputStream = null;
		// 创建 FileInputStream 对象
		FileInputStream fileInputStream = null;

		try {
			// 实例化 FileOutputStream 对象
			fileOutputStream = new FileOutputStream(zipFile);
			// 实例化 ZipOutputStream 对象
			zipOutputStream = new ZipOutputStream(fileOutputStream);
			// 创建 ZipEntry 对象
			ZipEntry zipEntry = null;
			// 定义每次读取的字节数组
			byte[] buffer = null ;
			if(byteLeng == 0 ){
				buffer = new byte[1024];
			}else{
				buffer = new byte[byteLeng];
			}

			int len;
			// 遍历源文件数组
			for (int i = 0; i < srcFiles.length; i++) {
				// 将源文件数组中的当前文件读入 FileInputStream 流中
				fileInputStream = new FileInputStream(srcFiles[i]);
				// 实例化 ZipEntry 对象，源文件数组中的当前文件
				zipEntry = new ZipEntry(srcFiles[i].getName());
				zipOutputStream.putNextEntry(zipEntry);
				// 遍历写入
				while ((len = fileInputStream.read(buffer)) > 0) {
					zipOutputStream.write(buffer, 0, len);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zipOutputStream.closeEntry();
				zipOutputStream.close();
				fileInputStream.close();
				fileOutputStream.close();
			}catch (IOException e){
				e.getMessage();
			}
		}
	}

	public static void zipFiles(File[] srcFiles, File zipFile){
		zipFiles(srcFiles,zipFile,1024);
	}

	/**
	 * 描述: 使用aspose-pdf技术进行ptf转word <br/>
	 *   1、无水印 <br/>
	 *   2、结果文字成段落格式，但是如果原pdf中的文字也是图片则结果也是整个的图，例如WPS-PDF的文字就是图片 <br/>
	 *   3、正常的文字格式加图片格式的PDF转换，结果可能存在多张图片合成了一张大图，这点需要用户自己裁剪一下 <br/>
	 *   4、全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/11/8 <br/>
	 * 参数: pdf-包含PDF的File对象,resultPath-结果路径,saveFormat-SaveFormat枚举值 <br/>
	 */
	public static void asposePDFToOrther(File pdf,String resultPath,int saveFormat) throws IOException {
		FileOutputStream os = new FileOutputStream(resultPath);
		Document doc = new Document(pdf.getPath());
		doc.save(os,saveFormat);
		os.close();
	}

	/**
	 * 描述: 使用spire技术进行pdf转word<br/>
	 *    1、有文本框水印，不过可以删除 <br/>
	 *    2、对应WPD-PDF可以转换成单个的文字图片 <br/>
	 * 	  3、对于正常的文字格式和图片格式PDF，结果是在段落的格式上每一行是一个文本框，并且基本不出现多张图片识别为一张的情况
	 * 	   不过图文一起解析时结果中的英文字体被所用技术定死为了Arial-OKITQ，在想办法解决，
	 *     单独转文本PDF不会出现英文字体的问题<br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/11/5 <br/>
	 * 参数: pdf-包含PDF的File对象,resultPath-结果路径,fileFormat-FileFormat枚举值 <br/>
	 */
	public static void spirePDFToOrther(File pdf,String resultPath,FileFormat fileFormat){
		PdfDocument pdfDocument = new PdfDocument();
		pdfDocument.loadFromFile(pdf.getPath());
		pdfDocument.saveToFile(resultPath, fileFormat);
		pdfDocument.close();
	}

}