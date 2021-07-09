# Desafio-PariPassu
<h4>SEJAM BEM VINDOS DEVS!!!</h4>
<hr/>
<p>Para testar a api você devera fazer um git clone na sua máquina ou baixar o projeto zipado e depois importar pra sua IDE.</p>
<p>Foi usada a IDE Spring Suit Tools para o desenvolvimento do backend e VSCode para o frontend.</p>
<p>Foi utilizado o postgres nessa aplicação, então por favor criar um banco e setar url, username, e password no applicantion-dev.properties</p>
<p>como por exemplo:</p>
<li>spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador-senhas</li>
<li>spring.datasource.username=postgres</li>
<li>spring.datasource.password=postgres</li>

<p>Depois de importar o backend para a sua IDE devera startar a aplicação como Spring Boot App.</p>
<br/>
<p>Segue o link da minha collection do Postman para ajudar nas requisições dos endpoints: https://www.getpostman.com/collections/dcd2edb8e3b578d83232</p>
<p>O link acima deverá ser importado dentro do seu Postman</p>
<p>Atenção: se não tiver uma variavel de ambiente host configurada, apague a variavel {{host}} e coloque http://localhost:8080/nomeEndPoint</p>
<p>Essa api possui 4 endpoints: /personagens, /locais, /users, /oauth/token </p>
