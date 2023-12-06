package br.uece.sgf.v1.apresentacao.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sgf.v1.apresentacao.dto.FuncionarioDto;
import br.uece.sgf.v1.apresentacao.dto.FuncionarioDtoMapper;
import br.uece.sgf.v1.negocio.Funcionario;
import br.uece.sgf.v1.negocio.ServicoFuncionario;

@RestController
@RequestMapping("/v1/funcionarios")
public class FuncionarioController {//(Antiga classe "InterfaceUsario.java"

	private final ServicoFuncionario funcionarioService;
	
	public FuncionarioController(ServicoFuncionario funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping("/ping")
	public String ping() {
		return "...pong!";
	}
	
	@GetMapping//Read
	public List<FuncionarioDto> listarTodos() {
		List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
		return FuncionarioDtoMapper.toDtoList(funcionarios);
	}
	
	@GetMapping("/{id}")//Read
	public FuncionarioDto obterPorId(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
		return FuncionarioDtoMapper.toDto(funcionario);
	}

	@PostMapping//create
	public FuncionarioDto criarFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
		Funcionario funcionario = FuncionarioDtoMapper.fromDto(funcionarioDto);
		funcionario = funcionarioService.criarFuncionario(funcionario);
		return FuncionarioDtoMapper.toDto(funcionario);
	}

	@DeleteMapping("/{id}")//Delete
	public void deletarFuncionario(@PathVariable Long id) {
		funcionarioService.removerFuncionario(new Funcionario(id));
	}
	
	@PutMapping("/{id}")//update
	public FuncionarioDto editarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDto) {
		funcionarioDto.setId(id);
		Funcionario funcionario = FuncionarioDtoMapper.fromDto(funcionarioDto);
		Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(funcionario);
	    return FuncionarioDtoMapper.toDto(funcionarioAtualizado);
	}

//    @PostMapping("/json/importar")
//    public void importarFuncionarios(@RequestBody List<FuncionarioDto> funcionariosImportados) {
//        funcionarioService.importarFuncionarios(FuncionarioDtoMapper.fromDtoList(funcionariosImportados));
//    }

//	@PostMapping("/csv/importar")
//	public void importarFuncionarios(@RequestParam("file") MultipartFile arquivo) throws IOException {
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
//
//			List<FuncionarioDto> funcionariosDto = new ArrayList<>();
//			String linha;
//
//			for (int numLinha = 1; (linha = reader.readLine()) != null; numLinha++) {
//				// Divida a linha em campos usando a vírgula como delimitador
//
//				String[] campos = linha.split(";");
//				if (numLinha > 1 && campos.length == 3) { // Certifique-se de que há 3 campos: ID, Nome e Telefone
//					String id = campos[0].trim();
//					String nome = campos[1].trim();
//					String telefone = campos[2].trim();
//
//					// Crie um objeto FuncionarioDto com os dados lidos
//					FuncionarioDto funcionarioDto = new FuncionarioDto();
//					funcionarioDto.setId(Long.parseLong(id));
//					funcionarioDto.setNome(nome);
//					funcionarioDto.setTelefone(telefone);
//					funcionariosDto.add(funcionarioDto);
//
//				}
//			}
//
//			// Chame o serviço para importar o funcionario
//			funcionarioService.importarFuncionarios(funcionariosDto);
//
//		} catch (IOException e) {
//			throw e;
//		}
//	}
//
//	@GetMapping("/csv/exportar")
//	public void exportar(HttpServletResponse response) {
//		response.setContentType("text/csv");
//		response.setHeader("Content-Disposition", "attachment; filename=\"funcionarios.csv\"");
//
//		List<Funcionario> funcionarios = funcionarioService.listarTodos();
//
//		try (PrintWriter writer = response.getWriter()) {
//			// Escreve o cabeçalho do CSV
//			writer.println("ID;Nome;Telefone");
//
//			// Escreve os dados dos funcionarios no CSV
//			for (Funcionario funcionario : funcionarios) {
//				writer.println(funcionario.getId() + ";" + funcionario.getNome() + ";" + funcionario.getTelefone());
//			}
//		} catch (IOException e) {
//			// Trate exceções, se necessário
//		}
//	}
}
