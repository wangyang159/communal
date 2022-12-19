package com.wy.utils;

import com.aspose.pdf.Document;
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
 * &nbsp;&nbsp;&nbsp;&nbsp;文件工具类 <br/>
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
	 * 参数: srcFiles-需要压缩的文件数组,zipFile-压缩结果文件路径,byteLeng--默认1024写入文件时的数组大小,del-是否删除原文件默认不删除 <br/>
	 * 返回值:  <br/>
	 */
	public static void zipFiles(File[] srcFiles, File zipFile , int byteLeng , boolean del) {
		// 判断压缩后的文件存在不，不存在则创建
		if (!zipFile.exists()) {
			try {
				zipFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 创建 ZipOutputStream 需要传入结果zip的fileoutput
		FileOutputStream fileOutputStream = null;
		ZipOutputStream zipOutputStream = null;
		ZipEntry zipEntry = null;
		//遍历文件时用的输入流
		File f = null;
		FileInputStream fileInputStream = null;

		try {
			// 打开结果zip的流
			fileOutputStream = new FileOutputStream(zipFile);
			zipOutputStream = new ZipOutputStream(fileOutputStream);

			// 定义每次读取的字节数组
			byte[] buffer = null ;
			if(byteLeng == 0 ){
				buffer = new byte[1024];
			}else{
				buffer = new byte[byteLeng];
			}

			// 遍历源文件数组
			int len;
			for (int i = 0; i < srcFiles.length; i++) {
				// 获取原文件流
				f = srcFiles[i];
				fileInputStream = new FileInputStream(f);
				// 当前文件转换为 ZipEntry 对象 加入到结果zip的流里面
				zipEntry = new ZipEntry(f.getName());
				zipOutputStream.putNextEntry(zipEntry);
				// 写入数据
				while ((len = fileInputStream.read(buffer)) > 0) {
					zipOutputStream.write(buffer, 0, len);
				}
				zipOutputStream.closeEntry();
				fileInputStream.close();
				//删除文件
				if(del){
					f.delete();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zipOutputStream.closeEntry();
				zipOutputStream.close();
				fileOutputStream.close();
			}catch (IOException e){
				e.getMessage();
			}
		}
	}

	public static void zipFiles(File[] srcFiles, File zipFile){
		zipFiles(srcFiles,zipFile,1024,false);
	}

	public static void zipFiles(File[] srcFiles, File zipFile,int byteLeng){
		zipFiles(srcFiles,zipFile,byteLeng,false);
	}

	public static void zipFiles(File[] srcFiles, File zipFile,boolean del){
		zipFiles(srcFiles,zipFile,1024,del);
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
		doc.close();
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