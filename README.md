# GTA VI Countdown — v0.1

## Como testar

Como o Gradle Wrapper (`gradlew`) é composto por arquivos binários, a forma
mais confiável de rodar isso é criar o projeto pelo próprio Android Studio
e depois substituir os arquivos gerados pelos deste pacote:

1. Abra o Android Studio → **New Project** → **Empty Activity** (com Compose).
2. Configure exatamente assim:
   - **Name:** GTA VI Countdown
   - **Package name:** `com.gtavicountdown.app`
   - **Minimum SDK:** API 26 (Android 8.0)
   - **Language:** Kotlin
3. Aguarde o projeto sincronizar uma vez (garante que o `gradlew` e as
   pastas `.idea`/`gradle` corretas já existem).
4. Substitua os seguintes arquivos gerados automaticamente pelos deste
   pacote (mesmo caminho):
   - `app/build.gradle.kts`
   - `app/src/main/AndroidManifest.xml`
   - `app/src/main/res/values/strings.xml`
   - `app/src/main/java/com/gtavicountdown/app/MainActivity.kt`
5. Adicione os arquivos novos (não existem no template padrão):
   - `app/src/main/java/com/gtavicountdown/app/config/CountdownConfig.kt`
   - `app/src/main/java/com/gtavicountdown/app/ui/home/CountdownState.kt`
   - `app/src/main/java/com/gtavicountdown/app/ui/home/HomeViewModel.kt`
   - `app/src/main/java/com/gtavicountdown/app/ui/home/HomeScreen.kt`
6. Rode o app em um emulador ou celular físico (▶ Run).

## O que você deve ver

- Fundo roxo escuro.
- "GTA VI COUNTDOWN" no topo.
- Dois cards: um para o **download** (12/11/2026) e outro para o
  **lançamento** (19/11/2026), cada um contando dias/horas/minutos/segundos
  e atualizando a cada segundo.

## Teste rápido do cálculo correto

Para confirmar que o contador não depende de um timer "burro" (que poderia
dessincronizar), você pode:

- Deixar o app aberto por 1-2 minutos e ver os segundos decrescendo
  normalmente.
- Colocar o app em segundo plano por alguns minutos e voltar — os valores
  devem estar corretos, refletindo o tempo real que passou.
- Trocar manualmente a data/hora do sistema no emulador para 18/11/2026 e
  reabrir o app — o card de lançamento deve mostrar poucas horas restantes.
