<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html> 
<html lang="pt-br">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>AQUAEMERGY</title>
  <link rel="stylesheet" href="CSS/estilo.css" />
  <link rel="shortcut icon" href="favicon/folha.png" type="image/x-icon" />
</head>
<body>

  <header>
    <nav>
      <div class="logo">
        <a href="index.html"><img src="IMAGENS/Aqua (1).png" alt="logo" /></a>
      </div>
      <ul>
        <li><a href="quemsomos.html" class="link">Quem Somos</a></li>
        <li><a onclick="window.scrollTo({top:845, behavior:'smooth'})" class="link">Notícias</a></li>
        <li><a onclick="window.scrollTo({top:2100, behavior:'smooth'})" class="link">Informações</a></li>
        <li><a onclick="window.scrollTo({top:1475, behavior:'smooth'})" class="link">Calculadora</a></li>
      </ul>
    </nav>
  </header>

  <main>
    <section id="IMG_PRINCIPAL">
      <img src="IMAGENS/emergia_img.png" alt="natural" />
    </section>

    <section class="noticias">
      <div class="destaques">
        <h2>DESTAQUES</h2>

        <article class="noticia">
          <img src="IMAGENS/natural_water.jpg" alt="Water natural" />
          <div class="meta">
            <span class="categoria">FREEAPIK NATURAL</span>
            <span class="data">24 abril 2025</span>
          </div>
          <h3>Entenda a importância da água na natureza...</h3>
        </article>

        <article class="noticia">
          <img src="IMAGENS/rain.jpg" alt="Rain Water" />
          <div class="meta">
            <span class="categoria">FREEPIK RAIN</span>
            <span class="data">10 abril 2025</span>
          </div>
          <h3>Como funciona a coleta da chuva...</h3>
        </article>

       
      </div>

      <aside class="ultimas">
        <h2>ÚLTIMAS NOTÍCIAS</h2>
        <ul>
          <p>A água é um dos elementos mais essenciais para a vida na Terra. Sem ela, nenhum ser vivo poderia sobreviver. Presente em todos os ecossistemas, a água participa diretamente dos ciclos biogeoquímicos, regula o clima, mantém os habitats naturais e possibilita a agricultura, a geração de energia e o abastecimento humano. Além disso, a água atua como solvente universal, transportando nutrientes e resíduos dentro dos organismos e no solo.

Na natureza, o ciclo hidrológico — conhecido como ciclo da água — é responsável por movimentar a água através da evaporação, condensação, precipitação e infiltração. Esse ciclo é movido principalmente pela energia solar e garante a renovação constante dos corpos d’água, como rios, lagos e aquíferos, além da umidade do solo e da atmosfera.</p>
          <p>A coleta da água da chuva, também chamada de captação pluvial, é uma prática sustentável que permite o aproveitamento da água que cai sobre telhados, coberturas ou superfícies impermeáveis. Essa água pode ser usada para fins não potáveis, como irrigação de jardins, lavagem de pisos, descarga de vasos sanitários, entre outros.
            Num cenário de mudanças climáticas e escassez hídrica em diversas regiões, utilizar a água da chuva representa uma atitude consciente e sustentável. Valorizar esse recurso natural é fundamental para garantir qualidade de vida no presente e para as futuras gerações. A água, em sua simplicidade, é a base da complexa rede de vida que sustenta nosso planeta.
          </p>
        </ul>
      </aside>
    </section>

    <section class="explicacao">
      <article class="Info">
        <h2 class="Question">Como funciona o calculo de emergia?</h2>
        <p class="Texto">
        O cálculo da emergia da água da chuva é uma abordagem ecológica e energética para entender quanto da energia disponível na natureza (geralmente solar) foi usada para produzir e disponibilizar essa água. Esse conceito vem da Análise Emergética, proposta por H.T. Odum, e é usado para avaliar o valor ambiental de recursos naturais, serviços ecossistêmicos e processos.
      O cálculo da emergia da água da chuva é uma abordagem ecológica e energética para entender quanto da energia disponível na natureza (geralmente solar) foi usada para produzir e disponibilizar essa água. Esse conceito vem da Análise Emergética, proposta por H.T. Odum, e é usado para avaliar o valor ambiental de recursos naturais, serviços ecossistêmicos e processos.
      </p>
      </article>
    </section>
<h2 id="chuva">Processo da coleta da chuva.</h2>
    <section class="icone-chuva">
  <div class="icone-container">
    <div class="icone-item">
      <img src="rain.png" alt="Ícone de chuva" />
      <p>Chuva</p>
    </div>
    <div class="icone-item">
      <img src="sand.png" alt="Ícone de reservatório" />
      <p>Reservatório</p>
    </div>
    <div class="icone-item">
      <img src="water-filter.png" alt="Ícone de filtro" />
      <p>Filtro</p>
    </div>
    <div class="icone-item">
      <img src="water-tap.png" alt="Ícone de torneira" />
      <p>Distribuição</p>
    </div>
  </div>


</section>

<form action="main" method="get" id="FormCalcular">
  <label for="volume">Volume mensal consumido de água (litros):</label>
  <input type="number" id="volume" name="volume" required>

  <label for="tipo">Tipo de água:</label>
  <select id="tipo" name="tipo" required>
    <option value="tratada">Água tratada urbana</option>
    <option value="subterranea">Água subterrânea</option>
    <option value="chuva">Água da chuva</option>
  </select>
  <button type="submit" class="btn-redirect">Calcular</button>
</form>


  </main>

  <footer>
    <p>&copy; 2025 AQUAEMERGY. Todos os direitos reservados.</p>
  </footer>

</body>
</html>

