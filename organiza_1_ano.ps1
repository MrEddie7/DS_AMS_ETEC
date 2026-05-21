# Organiza_1_ano.ps1
# ------------------------------------------------------------
# Script para organizar a pasta "1 ano" do repositório DS_AMS_ETEC
# Cria subpastas por linguagem e move cada projeto para o local correto.
# ------------------------------------------------------------

$basePath = "C:\Users\Admin\Documents\GitHub\DS_AMS_ETEC\1 ano"

# Definir as categorias
$categories = @{
    "Java"    = @("arrays", "matrix", "vetor", "exerciciosOrdemDeRepetição", "projeto_1505")
    "Web"     = @("calculadora")
    "ZIP"     = @("eduardo")
    "Scripts" = @("scripts", "scripts2")
    "Docs"    = @("README.md")
}

# Criar as subpastas se não existirem
foreach ($cat in $categories.Keys) {
    $targetDir = Join-Path $basePath $cat
    if (-not (Test-Path $targetDir)) {
        New-Item -ItemType Directory -Path $targetDir | Out-Null
        Write-Host "Created directory: $targetDir"
    }
}

# Função para mover itens
function Move-ItemSafely($item, $dest) {
    try {
        Move-Item -LiteralPath $item -Destination $dest -Force -ErrorAction Stop
        Write-Host "Moved $item -> $dest"
    } catch {
        Write-Warning "Failed to move $item: $_"
    }
}

# Processar cada categoria
foreach ($cat in $categories.Keys) {
    $destDir = Join-Path $basePath $cat
    foreach ($itemName in $categories[$cat]) {
        $sourcePath = Join-Path $basePath $itemName
        if (Test-Path $sourcePath) {
            Move-ItemSafely $sourcePath $destDir
        } else {
            Write-Warning "Item not found: $sourcePath"
        }
    }
}

# Log de conclusão
$logPath = Join-Path $basePath "organiza_1_ano.log"
"Organização concluída em $(Get-Date)" | Out-File -FilePath $logPath -Encoding utf8
Write-Host "Log written to $logPath"
