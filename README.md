Hier die einzelnen Schritte:

Phase 1:
Schritt 1: Domain-Daten bündeln
https://github.com/Cadarr/cleancode-seminar/commit/2fc4d56576079032dbf277699c7f123107bbd4b3

Schritt 2: Boundaries schneiden
https://github.com/Cadarr/cleancode-seminar/commit/5ae1e09d339585c16c1854e6a732f0dbb9baad86

Schritt 3: Happy Path freilegen 
Hier gab es keine Änderungen

Schritt 4: Abstraktionsebenen trennen
https://github.com/Cadarr/cleancode-seminar/commit/b415ee0b6a3584dd8e8ec777ee8ee3a67b37d76e

Schritt 5: Seiteneffekte lokalisieren
https://github.com/Cadarr/cleancode-seminar/commit/1a2f7b22d30bae241b762f2719fb8c28dcb2abf0

Schritt 6: Intention klarmachen
https://github.com/Cadarr/cleancode-seminar/commit/1e27177a59bf7b053e32bf9300c91cb57d717b48

Phase 2:
Schritt 7: Module/Klassen entlang der Boundaries schneiden
https://github.com/Cadarr/cleancode-seminar/commit/dfe887cf7b43b3856ed4f165df84eecb2b3379d6

Schritt 8: Abhängigkeiten umdrehen
https://github.com/Cadarr/cleancode-seminar/commit/29e279b02754a54ef27cc06fdc6ebefb12bd3414

Schritt 9: Polymorphie statt Bedingungen
Aktueller Stand...
https://github.com/Cadarr/cleancode-seminar/commit/3430273bd36967de7083f9abc6c55ae007761aa0




# 👋 Willkommen zum Clean-Code-Seminar

Schön, dass du da bist!

**Ziel dieses Projekts:**
Dieses Projekt enthält bewusst unaufgeräumten Code. Deine Aufgabe im Seminar ist, ihn Schritt für Schritt zu verbessern (Clean Code, Refactoring, Tests).

```text
   _____ _                      ____          _      
  / ____| |                    / ___|___   __| | ___ 
 | |    | | ___  __ _ _ __    | |   / _ \ / _` |/ _ \
 | |____| |/ _ \/ _` | '_ \   | |__| (_) | (_| |  __/
  \_____|_|\___/\__,_|_| |_|   \____\___/ \__,_|\___|

              C L E A N   C O D E
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
