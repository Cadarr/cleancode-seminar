# 👋 Willkommen zum Clean-Code-Seminar-Projekt

Schön, dass du dabei bist! Diese Readme hilft dir beim schnellen Einstieg, damit du das Projekt lokal auschecken, starten und in deinem eigenen Branch entwickeln kannst.

```text
   ____ _                          ____          _      
  / ___| | ___  __ _ _ __        / ___|___   __| | ___ 
 | |   | |/ _ \/ _` | '_ \_____ | |   / _ \ / _` |/ _ \
 | |___| |  __/ (_| | | | |_____| |__| (_) | (_| |  __/
  \____|_|\___|\__,_|_| |_|      \____\___/ \__,_|\___|
```

---

## 1) Voraussetzungen installieren

### Git installieren
Du brauchst Git, um den Code auszuchecken und mit Branches zu arbeiten.

- **Windows:** [https://git-scm.com/download/win](https://git-scm.com/download/win)
- **macOS:**
  ```bash
  brew install git
  ```
- **Linux (Debian/Ubuntu):**
  ```bash
  sudo apt update
  sudo apt install git
  ```

Prüfen:
```bash
git --version
```

### JDK installieren (Java Development Kit)
Für das Projekt brauchst du ein installiertes JDK.

Empfohlen: **JDK 17** (oder die im Seminar genannte Version).

- **Windows/macOS/Linux:** z. B. über Adoptium: [https://adoptium.net/](https://adoptium.net/)

Prüfen:
```bash
java -version
javac -version
```

---

## 2) Projekt auschecken

> ⚠️ Hinweis: Damit du auf das Repository pushen kannst, musst du vorher im Repository/der Organisation eingetragen sein.

Repository klonen:
```bash
git clone <REPO-URL>
cd clean-code-workshop
```

Beispiel für SSH:
```bash
git clone git@github.com:<org>/<repo>.git
cd clean-code-workshop
```

---

## 3) Projekt mit Maven Wrapper bauen & starten

Das Projekt enthält den **Maven Wrapper** (`mvnw` / `mvnw.cmd`).
Damit musst du Maven nicht separat installieren.

### Build + Tests ausführen
- **macOS/Linux:**
  ```bash
  ./mvnw clean test
  ```
- **Windows (PowerShell/CMD):**
  ```powershell
  .\mvnw.cmd clean test
  ```

### Anwendung starten
- **macOS/Linux:**
  ```bash
  ./mvnw exec:java -Dexec.mainClass="de.workshop.App"
  ```
- **Windows:**
  ```powershell
  .\mvnw.cmd exec:java -Dexec.mainClass="de.workshop.App"
  ```

---

## 4) Mit Branches arbeiten

### Zu einem vorhandenen Branch wechseln
```bash
git fetch
git switch <branch-name>
```

### Einen eigenen Branch anlegen
```bash
git switch -c feature/<dein-name>-<thema>
```

Beispiel:
```bash
git switch -c feature/max-refactoring-user-service
```

---

## 5) Änderungen committen

1. Dateien ändern
2. Status prüfen:
   ```bash
   git status
   ```
3. Änderungen stagen:
   ```bash
   git add .
   ```
4. Commit erstellen:
   ```bash
   git commit -m "feat: kurze beschreibung deiner änderung"
   ```
5. Branch hochladen:
   ```bash
   git push -u origin <dein-branch>
   ```

---

## 6) Kurze Team-Regeln fürs Seminar

- Arbeite immer in einem eigenen Branch.
- Schreibe kleine, verständliche Commits.
- Bei Fragen oder fehlenden Rechten: kurz beim Seminar-Team melden.
- **Wichtig:** Für Schreib-/Push-Rechte musst du im Repository eingetragen sein.

---

Viel Erfolg und vor allem viel Spaß beim gemeinsamen Clean-Coding! 🚀
