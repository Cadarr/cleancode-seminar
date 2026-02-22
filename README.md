# 👋 Willkommen zum Clean-Code-Seminar

Schön, dass du da bist!

**Ziel dieses Projekts:**
Dieses Projekt enthält bewusst unaufgeräumten Code. Deine Aufgabe im Seminar ist, ihn Schritt für Schritt zu verbessern (Clean Code, Refactoring, Tests).

```text
   ____ _                 _       ____          _      
  / ___| | ___  __ _ _ __| | __  / ___|___   __| | ___ 
 | |   | |/ _ \/ _` | '__| |/ / | |   / _ \ / _` |/ _ \
 | |___| |  __/ (_| | |  |   <  | |__| (_) | (_| |  __/
  \____|_|\___|\__,_|_|  |_|\_\  \____\___/ \__,_|\___|
```

---

## Schnellstart (fürs Seminar)

### 1) Voraussetzungen **prüfen**
> Wenn etwas fehlt: bitte kurz im Team melden oder vorab nachinstallieren.

```bash
git --version
java -version
```

Empfohlen ist **JDK 17** (oder die im Seminar genannte Version).

### 2) Repository klonen
```bash
git clone <REPO-URL>
cd clean-code-workshop
```

### 3) Tests ausführen (Maven Wrapper)
Du musst Maven nicht separat installieren, weil der Wrapper bereits im Projekt liegt.

- macOS/Linux:
  ```bash
  ./mvnw clean test
  ```
- Windows:
  ```powershell
  .\mvnw.cmd clean test
  ```

✅ Wenn die Tests laufen, bist du startklar.

---

## Optional (wenn im Seminar benötigt)

### Anwendung per Terminal starten
- macOS/Linux:
  ```bash
  ./mvnw exec:java -Dexec.mainClass="de.workshop.App"
  ```
- Windows:
  ```powershell
  .\mvnw.cmd exec:java -Dexec.mainClass="de.workshop.App"
  ```

### Branch wechseln oder eigenen Branch anlegen
```bash
git fetch
git switch <branch-name>
# oder neu:
git switch -c feature/<dein-name>-<thema>
```

### Änderungen committen
```bash
git add .
git commit -m "feat: kurze beschreibung"
```

### Push (nur wenn gewünscht)
```bash
git push -u origin <dein-branch>
```

> Hinweis: Für Push-/Schreibrechte musst du im Repository eingetragen sein.

---

Viel Erfolg und viel Spaß beim Refactoring! 🚀
