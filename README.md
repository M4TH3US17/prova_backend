<h1>Prova Verzel - backend</h1>
<strong>frontend: </strong> <a href="https://github.com/M4TH3US17/verzel_teste">Link do Frontend</a>

<h1>Ferramentas Usadas</h1>
<ul>
  <li>Java + Spring</li>
  <li>JWT</li>
  <li>Lombok</li>
  <li>Swagger</li>
  <li>PostgreSQL e FlyWay</li>
  <li>S3 (AWS)</li>
</ul>

<h1>Pré-requisitos:</h1>
<ul>
<li>Java</li>
<li>PostgreSQL + PgAdmin</li>
</ul>

<h2>Como Configurar o Projeto?</h2>
<h4>Crie, na AWS, um Bucket S3 para teste. Apos isso, va no application.properties e sete as seguintes informacoes:</h4>
<p>
# Amazon S3 <br>
<strong>s3.access-key-id:</strong> Sua chave de acesso do AWS IAM. <br>
<strong>s3.secret-key-id:</strong> Sua chave secreta do AWS IAM. <br>
<strong>s3.region:</strong> A região do seu bucket (por exemplo, us-east-1).  <br>
<strong>s3.bucket:</strong> O nome do bucket que você criou.
</p>

<h4>Configuracao do bando PostgreSQL</h4>
<p>
# DEV<br>
<strong>spring.datasource.url:</strong> Informe a URL do seu banco de dados<br>
<strong>spring.datasource.username:</strong> Nome de usuário para conexão ao banco<br>
<strong>spring.datasource.password:</strong> Senha para conexão ao banco
</p>

<h2>Como Acessar a documentação?</h2>
<p>Simples, basta executar o projeto no IntelliJ e digitar
 no seu navegador <code>http://localhost:8083/swagger-ui.html</code>. Em seguida, após abrir 
a documentação, vá em <mark>usuario-controller</mark> na parte de login e informe as credenciais
(login: admin, senha: admin) para gerar o Bearer Token.</p>

<h2>Como Rodar o Projeto?</h2>
<ul>
 <li>Baixe ou clone o projeto</li>
 <li>Abra sua IDE</li>
 <li>Importe o nosso projeto (Maven)</li>
 <li>Execute a aplicação springboot</li>
</ul>
