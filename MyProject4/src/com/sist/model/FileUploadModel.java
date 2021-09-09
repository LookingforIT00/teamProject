package com.sist.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.vo.FileVO;

@Controller
public class FileUploadModel {

	private String os;

	@RequestMapping("fileUpload.do")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

			os = System.getProperty("os.name").toLowerCase();

			Path uploadPath;
			if (os.contains("win")) {
				uploadPath = Paths.get("C:", "test");
			} else {
				uploadPath = Paths.get("/var", "test");
			}
			String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
			uploadPath = Paths.get(uploadPath.toString(), today);
			if (!Files.isDirectory(uploadPath)) {
				try {
					Files.createDirectory(uploadPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			File attachesDir = new File(uploadPath.toString());

			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(attachesDir);
			// fileItemFactory.setSizeThreshold(1024 * 1024);
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					throw new RuntimeException("error");
				} else {
					System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
							item.getName(), item.getSize());
					if (item.getSize() > 0) {
						String separator = File.separator;
						int index = item.getName().lastIndexOf(separator);
						String fileName = item.getName().substring(index + 1);
						File uploadFile = new File(uploadPath.toString() + separator + fileName);
						item.write(uploadFile);
					}
				}
			}
			
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
	}

	@RequestMapping("fileDownload.do")
	public String fileDownload(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		if(idx == null) {
			throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
		}
		//idx fileDTO
		FileVO fileInfo = new FileVO();

		String filePath = fileInfo.getSaveFile();
		File file = new File(filePath);
		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + java.net.URLEncoder.encode(fileInfo.getOriginalName(), "UTF-8") + "\";");
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();

			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error");
		}
	}
}