package com.claim.kidsstore.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.claim.kidsstore.model.User;
import com.claim.kidsstore.repository.UserRepository;


@Component
public class WebUtils {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	HttpServletRequest request;

	private static final String UPLOADED_FOLDER = "static" + File.separator + "img";

	@Autowired
	public WebUtils(JavaMailSender sender) {
		super();
		this.sender = sender;
	}
	
	public void sendMail(String to, String msg, String subject) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(to);
			helper.setText(msg);
			helper.setSubject(subject);
			
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}

	public void addProfilePhoto(MultipartFile file, long id, String folder)
			throws IllegalStateException, IOException {
		System.out.println("ID=" + id);
		try {

			// Save Dir
			String destDir = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER) + File.separator
					+ folder + File.separator + id + File.separator + "profile";

			// Initialize file
			File dir = new File(destDir);

			// If folder does not exist - create it
			if (!dir.exists()) {
				new File(destDir).mkdirs();
			}

			// Get file from form
			MultipartFile multipartFile = (MultipartFile) file;
			String fileName = file.getOriginalFilename();
			File doc = new File(destDir + File.separator + fileName);

			// Rename file
			File rename = new File(destDir + File.separator + "user_" + id + ".jpg");
			doc.renameTo(rename);

			// Save to file system
			multipartFile.transferTo(rename);
			User user = userRepository.findById(id).get();
			user.setImage("user_" + id + ".jpg");
			userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void multipleSave(MultipartFile[] files, long id) throws IllegalStateException, IOException {

		try {
			String realPathToUploads = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);

			File dir = new File(realPathToUploads + File.separator + id);

			List<String> fileNames = new ArrayList<String>();

			// Check if folder doesn't exist
			if (!dir.exists()) {
				dir.mkdirs();
			}
			System.out.println("ADDING MULTIPLE PHOTOS");
			if (files != null && files.length > 0) {
				for (MultipartFile file : files) {
					if (!file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					String filePath = realPathToUploads + File.separator + id + File.separator + fileName;
					System.out.println("UPLOADING");
					File destination = new File(filePath);
					try {
						file.transferTo(destination);
					} catch (Exception e) {
						e.printStackTrace();
					}

					fileNames.add(fileName);
					}
				}
			}
			System.out.println("UPLOADED");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeFiles(long id, String image) {

		try {
			String realPathToUploads = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);

			File file = new File(realPathToUploads + File.separator + id + File.separator + image + "");

			if (file.delete()) {
				if (userRepository.findById(id).get().getImage() != null) {
					User user = userRepository.findById(id).get();
					user.setImage("");
					userRepository.save(user);
				}

				System.out.println(file.getName() + "is deleted!");
			} else {
				System.out.println("Delete operation failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getFiles(Model model, long id) {
		List<String> results = new ArrayList<String>();

		try {
			String realPathToUploads = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);
			File[] files = new File(realPathToUploads + File.separator + id).listFiles();

			for (File file : files) {
				if (file.isFile()) {
					results.add(file.getName());
					model.addAttribute("filesname", results);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}