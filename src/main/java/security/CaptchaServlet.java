package security;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.awt.*;
//import Util;

public class CaptchaServlet extends HttpServlet {

	public static final String FILE_TYPE = "jpeg";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);

		String captchaStr = "";

		captchaStr = Util.generateCaptchaText(6);

		try {

			int width = 100;
			int height = 40;

			Color bg = new Color(0, 255, 255);
			Color fg = new Color(0, 100, 0);

			Font font = new Font("Arial", Font.BOLD, 20);
			BufferedImage cpimg = new BufferedImage(width, height,
					BufferedImage.OPAQUE);
			Graphics g = cpimg.createGraphics();

			g.setFont(font);
			g.setColor(bg);
			g.fillRect(0, 0, width, height);
			g.setColor(fg);
			g.drawString(captchaStr, 10, 25);

			HttpSession session = request.getSession(true);
			session.setAttribute("CAPTCHA", captchaStr);

			OutputStream outputStream = response.getOutputStream();

			ImageIO.write(cpimg, FILE_TYPE, outputStream);
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
