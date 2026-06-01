# ⚡ Controlador de Treino

Um aplicativo moderno e responsivo de controle de treinos desenvolvido com **Compose Multiplatform** e **Kotlin**, com um design premium e futurista no estilo *cyber-fitness*.

Desenvolvido por **Eduardo Cavalcante**.

---

## 🚀 Funcionalidades Principais

*   **Tela de Abertura (Splash Screen):**
    *   Animação pulsante com o logo do aplicativo e carregamento dinâmico.
    *   Transição automática de 2.5 segundos para a tela de autenticação.
*   **Sistema de Autenticação Completo:**
    *   Abas interativas para **Entrar (Login)** e **Cadastrar (Registro)**.
    *   Validação de dados em tempo real com avisos de erro e sucesso amigáveis.
    *   Persistência de usuários localmente no banco de dados SQLite.
    *   Suporte a visualização/ocultação de senhas.
    *   Usuário padrão pré-configurado para testes: `admin` / `admin`.
*   **Painel Geral (Dashboard):**
    *   Cálculo automático do volume total levantado (séries × repetições × carga) de todos os exercícios do dia.
    *   Barra de progresso geral e percentual de conclusão dos treinos diários.
*   **Controle e Criação de Treinos:**
    *   Criação de novos treinos informando nome e descrição.
    *   Opção de exclusão rápida e redefinição geral para os templates padrão.
*   **Gerenciamento Dinâmico de Exercícios:**
    *   Adicionar novos exercícios definindo séries, repetições e carga (kg).
    *   Ajustar parâmetros (séries, repetições e peso) de forma imediata através de botões de incremento/decremento rápido (`+`, `-`, `+2`, `-2`).
    *   Marcação de conclusão com riscado visual (*line-through*) e cálculo de progresso em tempo real.
*   **Cronômetro de Descanso Integrado (Rest Timer):**
    *   *Pill* flutuante animado na parte inferior que aparece automaticamente ao concluir um exercício.
    *   Controles de pausar, retomar, adicionar tempo (+15s) e fechar o timer.

---

## 🎨 Design System (Estética Cyber-Fitness)

O aplicativo conta com uma identidade visual escura premium inspirada em interfaces cibernéticas de alta performance:
*   **Fundo Escuro:** `#0F0F12` para máximo conforto visual.
*   **Roxo Neon:** `#8A2BE2` como cor primária de destaque.
*   **Ciano Cibernético:** `#00E5FF` para informações secundárias e botões de ação secundários.
*   **Verde Limão:** `#CCFF00` para indicadores de conclusão, progresso e sucesso.
*   **Vermelho Cyber:** `#FFFF453A` para erros e exclusões.

---

## 🛠️ Tecnologias Utilizadas

*   **Linguagem:** Kotlin
*   **Framework de UI:** Compose Multiplatform (Material 3)
*   **Persistência:** SQLite Database (através das classes nativas do Android `SQLiteOpenHelper`)
*   **Gerenciamento de Estado:** StateFlow / Kotlin Coroutines para atualizações reativas na UI

---

## ⚙️ Como Executar o Projeto

1.  Certifique-se de ter o **Android Studio** instalado.
2.  Clone este repositório em sua máquina.
3.  Abra o diretório raiz no Android Studio.
4.  Execute a sincronização do Gradle.
5.  Inicie o emulador Android ou conecte um dispositivo físico.
6.  Execute a aplicação no emulador/dispositivo (tarefa `:composeApp:installDebug` ou clicando em **Run** no Android Studio).

---

![Collando Collando GIF](collando-collando.gif)
