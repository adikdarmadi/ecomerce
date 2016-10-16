package com.ecomerce.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utilities for image manipulation.
 * 
 * @author Roberto
 */
public final class ImageUtil {

	/** The Constant LOG.logger */
	private static final Log LOG = LogFactory.getLog(ImageUtil.class);
	/** The Constant DEFAULT_IMAGE_FORMAT. The default image format is PNG */
	private static final String DEFAULT_IMAGE_FORMAT = "PNG";

	private ImageUtil() {
		// to prevent instantiation
	}

	/**
	 * Resize an image.
	 * 
	 * @param imageData
	 *            Source image in any recognized format (GIF, JPG, PNG)
	 * @param scaledWidth
	 *            New width after resizing
	 * @param scaledHeight
	 *            New height after resizing
	 * @param preserveAlpha
	 *            Set the value to false to keep the transparency
	 * @return Resized image
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] resizeImage(byte[] imageData, int scaledWidth,
			int scaledHeight, boolean preserveAlpha) throws IOException {
		if (ArrayUtils.isEmpty(imageData)) {
			return new byte[] {};
		}
		BufferedImage originalImage  = createImage(imageData);
		BufferedImage resizedImage = resizeImage(originalImage, scaledWidth,
				scaledHeight, preserveAlpha);
		return getImageBytes(resizedImage);
	}

	/**
	 * Resize an image.
	 * 
	 * @param image
	 *            Source image in any recognized format (GIF, JPG, PNG)
	 * @param scaledWidth
	 *            New width after resizing
	 * @param scaledHeight
	 *            New height after resizing
	 * @param preserveAlpha
	 *            Set the value to false to keep the transparency
	 * @return Resized image
	 */
	public static BufferedImage resizeImage(Image image, int scaledWidth,
			int scaledHeight, boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
				imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		// we prefer quality than speed
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}

	/**
	 * Create an image from byte array containing image data.
	 * 
	 * @param imageData
	 *            Source image in any recognized format (GIF, JPG, PNG)
	 * @return Valid image in any recognized format (GIF, JPG, PNG)
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static BufferedImage createImage(byte[] imageData)
			throws IOException {
		InputStream input = new ByteArrayInputStream(imageData);
		return ImageIO.read(input);
	}

	/**
	 * Store image binary data in a temporary file.
	 * 
	 * @param filename
	 *            The prefix of temporary file
	 * @param extension
	 *            The extension of temporary file, should be prefixed with dot
	 *            (.) sign
	 * @param imageData
	 *            the image data
	 * @return The url of temporary image file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String storeImage(String filename, String extension,
			byte[] imageData) throws IOException {
		String ext = extension;
		// extension must have dot (.) character in front of it
		if (extension.charAt(0) != '.') {
			ext = "." + ext;
		}
		// Open output file
		File outputFile = File.createTempFile(filename, ext);
		OutputStream out = new FileOutputStream(outputFile);
		try {
			out.write(imageData);
			out.flush();
		} finally {
			out.close();
		}
		// format the url of barcode file
		String url = outputFile.toURI().toString();
		// url = url.replace("file:", OpenOfficeUtil.FILE_SCHEME);
		LOG.info("Storing image in: " + url);
		return url;
	}

	/**
	 * Get array of byte data from an image after converting it into PNG format.
	 * 
	 * @param image
	 *            Source image in any recognized format (GIF, JPG, PNG)
	 * @return Byte array containing image data
	 * @throws IOException
	 *             If anything bad happen, tell the user.
	 */
	private static byte[] getImageBytes(BufferedImage image) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(image, DEFAULT_IMAGE_FORMAT, output);
		return output.toByteArray();
	}
}
