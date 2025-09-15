Para Rodar o Maven no codespace:

```bash
chmod +x ./mvnw
./mvnw spring-boot:run
```

1) Classe que mapeia a tabela

* @Entity → define que a classe é uma tabela.

* @Table(name = "tra_trabalho") → nome da tabela.

* @Id + @GeneratedValue → PK auto incremento.

* @Column(name="...") → mapear colunas.

* @ManyToOne + @JoinColumn → chave estrangeira.

```java
@Entity
@Table(name = "tra_trabalho")
public class Trabalho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tra_id")
    private Long id;

    @Column(name = "tra_titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "tra_data_hora_entrega", nullable = false)
    private LocalDateTime dataHoraEntrega;

    @Column(name = "tra_descricao")
    private String descricao;

    @Column(name = "tra_nota")
    private Integer nota;

    @ManyToOne
    @JoinColumn(name = "tra_usr_id", nullable = false)
    private Usuario usuario;

    // Getters e Setters
}
```

2) Repositório

* interface que estende JpaRepository.

* Métodos prontos: findAll(), save(), etc.

* Criar consulta customizada com @Query.

```java
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {

    @Query("SELECT t FROM Trabalho t JOIN t.usuario u " +
           "WHERE t.titulo LIKE %?1% AND u.nome LIKE %?2%")
    List<Trabalho> buscarPorTituloENomeUsuario(String titulo, String nomeUsuario);
}
```

3) Serviço

* Contém regra de negócio.

* 3 métodos pedidos:

    1. novoTrabalho → valida título e usuário, seta data/hora atual se nula.

    2. buscarTodos → retorna todos.

    3. buscarPorTituloENomeUsuario → usa o repo.

```java
@Service
public class TrabalhoServiceImpl implements TrabalhoService {

    private final TrabalhoRepository repo;

    public TrabalhoServiceImpl(TrabalhoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Trabalho novoTrabalho(Trabalho trabalho) {
        if (trabalho.getTitulo() == null || trabalho.getTitulo().isBlank() ||
            trabalho.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título e usuário obrigatórios!");
        }

        if (trabalho.getDataHoraEntrega() == null) {
            trabalho.setDataHoraEntrega(LocalDateTime.now());
        }

        return repo.save(trabalho);
    }

    @Override
    public List<Trabalho> buscarTodos() {
        return repo.findAll();
    }

    @Override
    public List<Trabalho> buscarPorTituloENomeUsuario(String titulo, String nomeUsuario) {
        return repo.buscarPorTituloENomeUsuario(titulo, nomeUsuario);
    }
}
```

4) Controller

* Atende na URL /trabalho.

* 3 rotas:

    1. POST /trabalho → cadastro.

    2. GET /trabalho → listar todos.

    3. GET /trabalho/tituloNomeUsuario → busca customizada.

```java
@RestController
@RequestMapping("/trabalho")
@CrossOrigin
public class TrabalhoController {

    private final TrabalhoService service;

    public TrabalhoController(TrabalhoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Trabalho> cadastrar(@RequestBody Trabalho trabalho) {
        Trabalho salvo = service.novoTrabalho(trabalho);
        return ResponseEntity.created(URI.create("/trabalho/" + salvo.getId())).body(salvo);
    }

    @GetMapping
    public List<Trabalho> listarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/tituloNomeUsuario")
    public List<Trabalho> buscarPorTituloENomeUsuario(
            @RequestParam String titulo,
            @RequestParam String nomeUsuario) {
        return service.buscarPorTituloENomeUsuario(titulo, nomeUsuario);
    }
}
```

Para Commit (adionando todos os arquivos modificados):
```bash
git add .
```

```bash
git commit -m "feat. adjustments"
```

```bash
git push origin main
```