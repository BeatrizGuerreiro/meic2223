package functions;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import metrics.Metric;
import metrics.MetricsLib;

public class Handler implements HttpFunction {
  
	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {
		final MetricsLib metrics = new MetricsLib();
        final String totalTimeTotal = metrics.startTime();

        BufferedWriter writer = response.getWriter();
        final String base64Image = request.getReader().readLine();

        try {
            final String decode64TimeTotal = metrics.startTime();
            byte[] imageBytes = Base64.getDecoder().decode(base64Image.getBytes(StandardCharsets.US_ASCII));
            BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            metrics.endTime(decode64TimeTotal);

            final String grayscaleTimeTotal = metrics.startTime();
            BufferedImage outputImage = convertToGrayscale(inputImage, metrics);
            metrics.endTime(grayscaleTimeTotal);

            final String encode64TimeTotal = metrics.startTime();
            String grayscaleBase64 = encodeToBase64(outputImage);
            metrics.endTime(encode64TimeTotal);

            response.setStatusCode(200);
            response.setContentType("image/png");
            writer.write(grayscaleBase64);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(500);
            writer.write(e.toString());
        }

        metrics.endTime(totalTimeTotal);
        String end = metrics.end();
	}
  
    // Function to process an image and convert it to grayscale
    public static BufferedImage convertToGrayscale(BufferedImage inputImage, MetricsLib metrics) {

        Metric incMetric = null;

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(inputImage.getRGB(x, y));
                int grayValue = (int) (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
                Color grayscaleColor = new Color(grayValue, grayValue, grayValue);
                outputImage.setRGB(x, y, grayscaleColor.getRGB());

                incMetric = metrics.incCounter(incMetric != null ? incMetric.getName() : null);
            }
        }

        return outputImage;
    }

    // Function to encode a BufferedImage to a base64 string
    public static String encodeToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}