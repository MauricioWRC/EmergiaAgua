package controller;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CalculadoraEmergia;
import model.Etapa;
import java.util.List;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Phrase;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;


import model.Recurso;



@WebServlet(urlPatterns = {"/Controller", "/main"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String volumeStr = request.getParameter("volume");
		String tipo = request.getParameter("tipo");
		boolean gerarPdf = "true".equals(request.getParameter("pdf"));


		double volumeLitros = 0.0;
		double resultadoEmergiaPorLitro = 0.0;

		try {
			volumeLitros = Double.parseDouble(volumeStr);
		} catch (Exception e) {
			response.getWriter().write("Erro: volume inválido.");
			return;
		}

		CalculadoraEmergia calculator = new CalculadoraEmergia();

		switch (tipo) {
			case "tratada":
				resultadoEmergiaPorLitro = calculator.calcularEmergiaAguaTratada();
				break;
			case "subterranea":
				resultadoEmergiaPorLitro = calculator.calcularEmergiaAguaSubterranea();
				break;
			case "chuva":
				resultadoEmergiaPorLitro = calculator.calcularEmergiaAguaChuva();
				break;
			default:
				response.getWriter().write("Erro: tipo de água inválido.");
				return;
		}

		double resultadoEmergia = resultadoEmergiaPorLitro * volumeLitros;

		double mediaDiaria = resultadoEmergia / 30.0;
	    double mediaMensal = resultadoEmergia;         
		double mediaAnual = resultadoEmergia * 12.0;
		
		
		List<Etapa> etapas = switch (tipo) {
	    case "tratada" -> calculator.getEtapasAguaTratada();
	    case "subterranea" -> calculator.getEtapasAguaSubterranea();
	    case "chuva" -> calculator.getEtapasAguaChuva();
	    default -> List.of();
	    };
	    
	    
	    LocalDate hoje = LocalDate.now();
		String data = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		
		if (gerarPdf) {
			gerarRelatorioPdf(response, data, volumeLitros, tipo, resultadoEmergia, mediaDiaria, mediaMensal, mediaAnual, etapas);
		    return;
		}
		

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang='pt-br'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Relatório de Emergia</title>");
		out.println("<style>");
		out.println("  body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
		out.println("  .container { background: white; max-width: 600px; margin: auto; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
		out.println("  h2 { text-align: center; color: #2c3e50; }");
		out.println("  p { font-size: 16px; margin: 8px 0; }");
		out.println("  .highlight { background: #ecf0f1; padding: 10px; border-radius: 5px; }");
		out.println("  .section-title { margin-top: 20px; font-weight: bold; color: #2980b9; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");

		out.println("<h2>Relatório de Emergia</h2>");
		out.println("<p><span class='section-title'>Data:</span> " + data + "</p>");
		out.println("<p><span class='section-title'>Volume informado:</span> " + volumeLitros + " litros</p>");
		out.println("<p><span class='section-title'>Tipo de água:</span> " + traduzirTipo(tipo) + "</p>");

		out.println("<div class='highlight'>");
		out.printf("<p><strong>Emergia total:</strong> %.2e sej</p>", resultadoEmergia);
		out.println("</div>");

		out.println("<p class='section-title'>Médias:</p>");
		out.printf("<p>Diária: %.2e sej/dia</p>", mediaDiaria);
		out.printf("<p>Mensal: %.2e sej/mês</p>", mediaMensal);
		out.printf("<p>Anual: %.2e sej/ano</p>", mediaAnual);
		
		out.println("<form action='main' method='get'>");
		out.println("<input type='hidden' name='volume' value='" + volumeLitros + "'>");
		out.println("<input type='hidden' name='tipo' value='" + tipo + "'>");
		out.println("<input type='hidden' name='pdf' value='true'>");
		out.println("<button type='submit'>Baixar PDF</button>");
		out.println("</form>");


		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
	
	private void gerarRelatorioPdf(HttpServletResponse response, String data, double volumeLitros, String tipo,
			double resultadoEmergia, double mediaDiaria, double mediaMensal, double mediaAnual, List<Etapa> etapas) {
	    try {
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "inline; filename=relatorio-emergia.pdf");

	        com.lowagie.text.Document document = new com.lowagie.text.Document();
	        com.lowagie.text.pdf.PdfWriter.getInstance(document, response.getOutputStream());
	        document.open();

	        String caminhoImagem = getServletContext().getRealPath("/IMAGENS/Aqua (1).png");
	        com.lowagie.text.Image logo = com.lowagie.text.Image.getInstance(caminhoImagem);
	        logo.scaleToFit(100, 100);
	        logo.setAlignment(com.lowagie.text.Image.ALIGN_CENTER);
	        document.add(logo);
	        document.add(new Paragraph(" "));
	        
	        com.lowagie.text.Font titulo = new com.lowagie.text.Font(com.lowagie.text.Font.HELVETICA, 18, com.lowagie.text.Font.BOLD, Color.DARK_GRAY);
	        com.lowagie.text.Font subtitulo = new com.lowagie.text.Font(com.lowagie.text.Font.HELVETICA, 13, com.lowagie.text.Font.BOLD, Color.BLUE);
	        com.lowagie.text.Font normal = new com.lowagie.text.Font(com.lowagie.text.Font.HELVETICA, 12, com.lowagie.text.Font.NORMAL, Color.BLACK);

	      

	        
	        document.add(new com.lowagie.text.Paragraph("Relatório de Emergia", titulo));
	        document.add(new com.lowagie.text.Paragraph(" "));
	        
	        
	        document.add(new Paragraph(
	        	    "Obs.: Os valores acima consideram o volume total informado pelo usuário.",
	        	    normal
	        	));
	        document.add(new com.lowagie.text.Paragraph("Volume informado: " + volumeLitros + " litros", normal));
	        document.add(new com.lowagie.text.Paragraph("Tipo de água: " + traduzirTipo(tipo), normal));
	        document.add(new com.lowagie.text.Paragraph(" "));

	        document.add(new com.lowagie.text.Paragraph(String.format("Emergia total: %.2e sej", resultadoEmergia), subtitulo));
	        document.add(new com.lowagie.text.Paragraph(" "));

	        document.add(new com.lowagie.text.Paragraph("Médias:", subtitulo));
	        document.add(new com.lowagie.text.Paragraph(String.format("Diária: %.2e sej/dia", mediaDiaria), normal));
	        document.add(new com.lowagie.text.Paragraph(String.format("Mensal: %.2e sej/mês", mediaMensal), normal));
	        document.add(new com.lowagie.text.Paragraph(String.format("Anual: %.2e sej/ano", mediaAnual), normal));
	        document.add(new com.lowagie.text.Paragraph(" "));
	        document.add(new com.lowagie.text.Paragraph("Detalhamento por etapa:", subtitulo));
	        document.add(new com.lowagie.text.Paragraph(" "));

	        document.add(new Paragraph(
	        	    "A tabela abaixo apresenta os valores de energia, transformidade e emergia por litro de água, conforme cada etapa do processo.",
	        	    normal
	        	));

	        
	        
	        PdfPTable tabela = new PdfPTable(4);
	        tabela.setWidthPercentage(100);
	        tabela.setSpacingBefore(10);
	        
	        

	        tabela.addCell(new Phrase("Etapa/Fluxo", subtitulo));
	        tabela.addCell(new Phrase("Energia (J)", subtitulo));
	        tabela.addCell(new Phrase("Transformidade (sej/J)", subtitulo));
	        tabela.addCell(new Phrase("Emergia (sej/ano)", subtitulo));

	        for (Etapa etapa : etapas) {
	            for (Recurso recurso : etapa.getRecursos()) {
	                tabela.addCell(new Phrase(etapa.getNome(), normal));
	                tabela.addCell(new Phrase(String.format("%.2f", recurso.getEnergiaJoules()), normal));
	                tabela.addCell(new Phrase(String.format("%.2e", recurso.getTransformidade()), normal));
	                tabela.addCell(new Phrase(String.format("%.2e", recurso.calcularEmergia()), normal));
	            }
	        }

	        double somaEmergia = etapas.stream()
	            .flatMap(etapa -> etapa.getRecursos().stream())
	            .mapToDouble(Recurso::calcularEmergia)
	            .sum();

	        tabela.addCell(new Phrase("TOTAL /L)", subtitulo));
	        tabela.addCell(new Phrase("-", normal));
	        tabela.addCell(new Phrase("-", normal));
	        tabela.addCell(new Phrase(String.format("%.2e", somaEmergia), subtitulo));

	        document.add(tabela);
	        Font observacao = new Font(Font.HELVETICA, 9, Font.ITALIC, Color.GRAY);
	        document.add(new Paragraph(
	            "Nota: Os valores de energia e transformidade não são somados. A linha TOTAL refere-se apenas à emergia total por litro.",
	            observacao
	        ));



	        Font rodape = new Font(Font.HELVETICA, 9, Font.ITALIC, Color.GRAY);
	        document.add(new Paragraph("Fonte: Adaptado de Odum (1996), Pereira (2008), Brown et al.", rodape));

	        

	        
	        
	        document.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            response.getWriter().write("Erro ao gerar PDF.");
	        } catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
	    }
}




	private String traduzirTipo(String tipo) {
		switch (tipo) {
			case "tratada": return "Água tratada urbana";
			case "subterranea": return "Água subterrânea";
			case "chuva": return "Água da chuva";
			default: return "Tipo desconhecido";
		}
	}
	
	//Gerar pdf
	
}
