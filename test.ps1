$repoPath = "$env:USERPROFILE\.m2\repository"
Get-ChildItem -Path $repoPath -Recurse -Filter "*.jar" | ForEach-Object {
    $jar = $_.FullName
    $entries = [System.IO.Compression.ZipFile]::OpenRead($jar).Entries
    foreach ($entry in $entries) {
        if ($entry.FullName -like "*.class") {
            $stream = $entry.Open()
            $reader = New-Object System.IO.StreamReader($stream)
            $content = $reader.ReadToEnd()
            if ($content -match "EnableNeo4jRepositories") {
                Write-Host "FOUND in JAR: $jar -> $($entry.FullName)"
            }
            $reader.Close()
            $stream.Close()
        }
    }
}
