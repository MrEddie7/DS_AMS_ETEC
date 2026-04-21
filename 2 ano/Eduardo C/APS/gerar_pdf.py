from reportlab.lib.pagesizes import A4
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle, PageBreak
from reportlab.lib.styles import getSampleStyleSheet
from reportlab.lib import colors
from reportlab.lib.units import cm

# Criar PDF final com 10 cenários, 2 por página
output_path = r"C:\Users\dti\Documents\Resolucao_10_Cenarios.pdf"
doc = SimpleDocTemplate(output_path, pagesize=A4)
styles = getSampleStyleSheet()
story = []

def add_cenario(numero, titulo, descricao, classes, atributos, metodos, cardinalidade, explicacao):
    story.append(Paragraph(f"<b>Cenário {numero} – {titulo}</b>", styles["Heading2"]))
    story.append(Spacer(1, 6))
    story.append(Paragraph(descricao, styles["Normal"]))
    story.append(Spacer(1, 6))

    # Montar tabela
    data = [[
        Paragraph("Classes", styles["Normal"]),
        Paragraph("Atributos", styles["Normal"]),
        Paragraph("Métodos", styles["Normal"]),
        Paragraph("Cardinalidade", styles["Normal"])
    ]]
    max_len = max(len(classes), len(atributos), len(metodos), len(cardinalidade))
    for i in range(max_len):
        data.append([
            Paragraph(classes[i], styles["Normal"]) if i < len(classes) else Paragraph("", styles["Normal"]),
            Paragraph(atributos[i], styles["Normal"]) if i < len(atributos) else Paragraph("", styles["Normal"]),
            Paragraph(metodos[i], styles["Normal"]) if i < len(metodos) else Paragraph("", styles["Normal"]),
            Paragraph(cardinalidade[i], styles["Normal"]) if i < len(cardinalidade) else Paragraph("", styles["Normal"])
        ])
    table = Table(data, colWidths=[4*cm, 6*cm, 5*cm, 4*cm])
    table.setStyle(TableStyle([
        ("BACKGROUND", (0,0), (-1,0), colors.grey),
        ("TEXTCOLOR", (0,0), (-1,0), colors.whitesmoke),
        ("ALIGN", (0,0), (-1,-1), "LEFT"),
        ("FONTNAME", (0,0), (-1,0), "Helvetica-Bold"),
        ("FONTSIZE", (0,0), (-1,-1), 8),
        ("VALIGN", (0,0), (-1,-1), "TOP"),
        ("WORDWRAP", (0,0), (-1,-1), True),
        ("GRID", (0,0), (-1,-1), 0.5, colors.black),
    ]))
    story.append(table)
    story.append(Spacer(1, 6))

    story.append(Paragraph(f"<b>Explicação:</b> {explicacao}", styles["Normal"]))
    story.append(Spacer(1, 12))

# Dados dos 10 cenários (descrição resumida, tabelas, explicações)
cenarios = [
    (1, "Biblioteca Interativa",
     "Biblioteca moderna que oferece livros físicos e digitais. Usuários podem realizar empréstimos e deixar comentários.",
     ["Usuario", "Emprestimo", "Livro", "Comentario"],
     [
         "codigo, nome, email, telefone, dataCadastro",
         "id, dataRetirada, prevDevolucao, dataDevolucao, status, valorMulta",
         "id, titulo, autores, isbn, anoPublicacao, formato",
         "id, texto, data, nota"
     ],
     [
         "realizarEmprestimo(), devolverLivro(), comentarLivro()",
         "calcularMulta(), marcarDevolvido(), estaAtivo()",
         "marcarIndisponivel(), marcarDisponivel(), obterStatus()",
         "editar(), remover()"
     ],
     [
         "Usuario 1 —— 0..* Emprestimo",
         "Emprestimo 1 —— 1 Livro",
         "Usuario 1 —— 0..* Comentario",
         "Livro 1 —— 0..* Comentario"
     ],
     "Relacionamento central: Usuario → Emprestimo → Livro."
    ),
    (2, "Escola de Música Online",
     "Escola online com alunos, cursos, professores e matrículas. Cursos podem gerar certificados e alunos possuem planos de assinatura.",
     ["Aluno", "Curso", "Professor", "Matricula", "Certificado", "PlanoAssinatura"],
     [
         "matricula, nome, email, dataNascimento",
         "id, titulo, modalidade, cargaHoraria, nivel",
         "id, nome, especialidade, biografia",
         "id, status, notaFinal, frequencia",
         "id, dataEmissao, codigoVerificacao",
         "id, tipo, dataInicio, dataTermino, status, valorPago"
     ],
     [
         "inscreverCurso(), cancelarMatricula(), baixarCertificado()",
         "abrirTurma(), fecharTurma()",
         "criarCurso(), avaliarAluno()",
         "atualizarNota(), atualizarFrequencia(), concluir()",
         "validarCodigo()",
         "renovar(), cancelar()"
     ],
     [
         "Aluno N —— M Curso via Matricula",
         "Curso 1 —— * Matricula",
         "Professor 1 —— * Curso",
         "Matricula 0..1 —— 1 Certificado",
         "Aluno 1 —— * PlanoAssinatura"
     ],
     "Relacionamento N:M entre Aluno e Curso via Matricula."
    ),
    (3, "Restaurante Inteligente",
     "Sistema de pedidos com clientes, garçons, pedidos, itens e produtos.",
     ["Cliente", "Garcom", "Pedido", "ItemPedido", "Produto"],
     ["id, nome, telefone","id, nome, matricula","id, mesa, dataAbertura, dataFechamento, status","id, quantidade, observacao, precoUnitario","id, nome, descricao, tipo, preco"],
     ["abrirPedido()","assumirPedido(), finalizarPedido()","adicionarItem(), removerItem(), calcularTotal(), alterarStatus()","calcularSubtotal()","atualizarPreco()"],
     ["Cliente 1 —— * Pedido","Garcom 1 —— * Pedido","Pedido 1 —— * ItemPedido","Produto 1 —— * ItemPedido"],
     "Relacionamento N:M entre Pedido e Produto via ItemPedido."
    ),
    (4, "Hospital com Telemedicina",
     "Sistema hospitalar com pacientes, médicos, consultas, receitas, internações e leitos.",
     ["Paciente","Medico","Consulta","Receita","Internacao","Leito"],
     ["id, nome, dataNascimento, documento, planoSaude","id, nome, crm, especialidade","id, data, hora, tipo, queixa, diagnostico, status","id, data, conteudo, validade","id, dataEntrada, dataSaida, status","id, numero, ala, tipo, status"],
     ["agendarConsulta(), solicitarInternacao()","registrarDiagnostico(), emitirReceita()","alterarStatus(), adicionarReceita()","validarValidade()","transferirLeito(), darAlta()","ocupar(), liberar()"],
     ["Paciente 1 —— * Consulta","Medico 1 —— * Consulta","Consulta 1 —— * Receita","Paciente 1 —— * Internacao","Internacao 1 —— 1 Leito"],
     "Controle completo de consultas, receitas e internações."
    ),
    (5, "Cinema com Reserva de Lugares",
     "Sistema de cinema com filmes, salas, sessões, clientes, ingressos, assentos e combos.",
     ["Filme","Sala","Sessao","Cliente","Assento","Ingresso","Combo"],
     ["id, titulo, genero, duracao","id, nome, capacidade, tipo","id, data, hora, idioma, legenda, precoBase","id, nome, email","id, fila, numero, tipo","id, codigoQR, precoFinal","id, nome, descricao, preco"],
     ["","", "calcularPrecoBase()","comprarIngresso()","","gerarQRCode(), cancelar()","aplicarDesconto()"],
     ["Filme 1 —— * Sessao","Sessao 1 —— 1 Sala","Sessao 1 —— * Ingresso","Cliente 1 —— * Ingresso","Ingresso 1 —— 1 Assento","Ingresso 0..* —— 0..* Combo"],
     "Ingressos conectam Cliente, Sessão e Assento."
    ),
    (6, "Marketplace de Artesanato",
     "Plataforma de vendas com artesãos, produtos, clientes, pedidos, avaliações e comissão.",
     ["Artesao","Produto","Cliente","Pedido","ItemPedido","Avaliacao","Comissao"],
     ["id, nome, email, reputacao","id, titulo, descricao, preco, estoque, tipo, prazoProducao","id, nome, email, enderecoEntrega","id, data, status, subtotal, frete, total","id, quantidade, personalizacao, precoUnitario","id, nota, comentario, data","id, percentual, valorCalculado"],
     ["cadastrarProduto(), atualizarProduto()","reservarEstoque(), liberarEstoque()","","calcularTotais(), pagar()","calcularSubtotal()","editar(), remover()","calcular()"],
     ["Artesao 1 —— * Produto","Cliente 1 —— * Pedido","Pedido 1 —— * ItemPedido","Produto 1 —— * ItemPedido","Produto 1 —— * Avaliacao","Cliente 1 —— * Avaliacao","Pedido 1 —— 1 Comissao"],
     "Pedidos geram comissão e podem conter produtos personalizados."
    ),
    (7, "Hotel com Check-in Automático",
     "Sistema hoteleiro com hóspedes, reservas, quartos e serviços extras.",
     ["Hospede","Reserva","Quarto","ServicoExtra"],
     ["id, nome, documento, email, telefone","id, dataEntradaPrev, dataSaidaPrev, status, codigoAcesso","id, numero, tipo, valorDiaria, statusOcupacao","id, tipo, descricao, preco"],
     ["realizarReserva(), cancelarReserva(), fazerCheckin()","adicionarServico(), removerServico(), calcularTotal()","reservar(), liberar()","aplicar()"],
     ["Hospede 1 —— * Reserva","Reserva 1 —— 1 Quarto","Quarto 1 —— * Reserva","Reserva N —— M ServicoExtra"],
     "Reservas automáticas com serviços extras."
    ),
    (8, "Academia com Treinos Personalizados",
     "Sistema de academia com instrutores, alunos, planos de treino, exercícios, equipamentos e registros.",
     ["Instrutor","Aluno","PlanoTreino","ItemPlano","Exercicio","RegistroTreino","Equipamento"],
     ["id, nome, cref, especialidade","id, nome, dataNascimento, objetivo","id, dataInicio, dataTermino, objetivo","id, series, repeticoes, carga","id, nome, grupoMuscular","id, data, pesoUtilizado, repeticoesRealizadas, observacoes","id, nome, fabricante, modelo"],
     ["atribuirPlano(), acompanharAluno()","iniciarPlano(), registrarExecucao()","adicionarItem(), removerItem()","ajustarCarga()","descreverTecnica()","salvar()","inspecionar()"],
     ["Instrutor N —— M Aluno","Aluno 1 —— * PlanoTreino","PlanoTreino 1 —— * ItemPlano","Exercicio 1 —— * ItemPlano","Aluno 1 —— * RegistroTreino","Equipamento 1 —— * RegistroTreino"],
     "Planos personalizados com exercícios ligados a equipamentos."
    ),
    (9, "Loja Virtual de Eletrônicos",
     "Loja online com clientes, produtos, pedidos, pagamentos e suporte.",
     ["Cliente","Produto","Pedido","Pagamento","Suporte"],
     ["id, nome, email, endereco","id, nome, descricao, preco, estoque, garantia","id, data, status, valorTotal","id, data, valor, metodo, status","id, data, descricao, status"],
     ["realizarPedido(), abrirChamado()","atualizarPreco(), reduzirEstoque()","calcularTotal(), atualizarStatus()","confirmar(), estornar()","atender(), encerrar()"],
     ["Cliente 1 —— * Pedido","Pedido 1 —— * Produto","Pedido 1 —— 1 Pagamento","Cliente 1 —— * Suporte"],
     "Gestão de vendas, estoque e suporte ao cliente."
    ),
    (10, "Sistema de Transporte Urbano",
     "Plataforma de transporte com passageiros, motoristas, corridas, veículos e pagamentos.",
     ["Passageiro","Motorista","Corrida","Veiculo","Pagamento"],
     ["id, nome, email, telefone","id, nome, cnh, avaliacao","id, origem, destino, data, hora, valor, status","id, placa, modelo, ano, capacidade","id, data, valor, metodo, status"],
     ["solicitarCorrida(), avaliarMotorista()","aceitarCorrida(), finalizarCorrida()","iniciar(), encerrar()","registrarManutencao()","confirmar(), estornar()"],
     ["Passageiro 1 —— * Corrida","Motorista 1 —— * Corrida","Corrida 1 —— 1 Veiculo","Corrida 1 —— 1 Pagamento"],
     "Corridas conectam Passageiro, Motorista, Veículo e Pagamento."
    ),
]

# Montar o PDF com dois cenários por página
for i, c in enumerate(cenarios, start=1):
    add_cenario(*c)
    if i % 2 == 0 and i != len(cenarios):
        story.append(PageBreak())

doc.build(story)
output_path
