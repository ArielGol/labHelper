# LabHelper
Sistema de gestión de laboratorio para registrar, organizar y dar seguimiento al ciclo completo de análisis de muestras,ensayos y resultados.
---
**PROGRAMA INTENSIVO- CODIGO MARIANO**
---
## Primer Semana
### Objetivos

- [x] Cuestionario para definir tu proyecto
- [x] Mini programa en Java


### Cuestionario
**1. Identidad de la aplicación**
-¿Qué nombre le pondrías a tu aplicación?
NOMBRE APP: LabHelper.
- ¿En qué temática o rubro se basará (educación,salud, finanzas,entretenimiento,etc.)?
Está orientada al rubro de gestión de laboratorio, particulamente laboratorios químicos, ambientales, industriales, de  investigación o control de calidad.
Sistema de gestión de laboratorio para registrar, organizar y dar seguimiento al ciclo completo de analisis de muestras, ensayos y resultados.
-En una frase corta, ¿cómo describirías la idea principal de tu aplicación?
Organizar las muestras , ensayos y resultados de los experimentos y/o servicios.

**2.Usuarios y perfiles**
- ¿Quiénes serían los usuarios principales de la aplicación?
Los usuarios principales: analistas o técnicos de laboratorio.
- ¿Qué roles o perfiles diferentes existirían (ej: administrador, usuario estándar, invitado)?
Roles:
Analistas/tecnicos de laboratorio : responsable de registrar muestras, ensayos y cargar los resultados.
Supervisor/Coordinador: revisa, verifica y aprueba resultados antes de emitir un informe de los mismos.
Administrador del sistema: configura el sistema.
- ¿Qué diferencia habría entre lo que puede hacer cada perfil?


| Administrador | Analista | Supervisor |
|:--------|:------:|------:|
| Crear usuarios | Registrar muestras | Visualizar resultados cargados |
| Editar usuarios | Consultar muestras | Aprobar resultados |
| Asignar roles | Asignar ensayos | Rechazar resultados |
| Gestionar tipos de ensayos | Cargar resultados | Solicitar repetición del análisis |
| Gestionar equipos | Modificar resultados antes de aprobación | Cerrar muestras finalizadas |
| Consultar toda la información | | |


**3.Funcionalidades principales**
¿Cuáles son las 3 funcionali
Unordered lists use dashes, asterisks, or plus signs:

- Import files from GitHub, Dropbox, or Google Drive
- Export to Markdown, HTML, or PDF
- Drag and drop files directly into the editor

Ordered lists are numbered automatically:

1. Write your markdown
2. Preview the rendered output
3. Export or save to the cloud

Nested lists work too:

- Cloud integrations
  - GitHub repositories
  - Dropbox folders
  - Google Drive files
  - OneDrive and Bitbucket
- Local features
  - Auto-save to browser storage
  - Image paste from clipboard

## Task Lists

- [x] Set up the editor
- [x] Write some markdown
- [ ] Connect a cloud service
- [ ] Export the finished document

## Links and Images

Link to any page with [inline links](https://dillinger.io) or use [reference-style links][dillinger].

Images use a similar syntax:

![Placeholder](https://placehold.co/600x200/2B2F36/35D7BB?text=Your+Image+Here)

[dillinger]: https://dillinger.io

## Blockquotes

> The art of writing is the art of discovering what you believe.
>
> — Gustave Flaubert

Blockquotes can contain other markdown elements:

> **Tip:** Use `Cmd+Shift+Z` to enter zen mode for distraction-free writing.

## Code

Fenced code blocks support syntax highlighting:

```javascript
function greet(name) {
  return `Hello, ${name}.`;
}

console.log(greet("world"));
```

```python
def fibonacci(n):
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    return a
```

## Tables

| Shortcut | Action |
|----------|--------|
| `⌘ ⇧ Z` | Toggle zen mode |
| `Escape` | Exit zen mode |
| `?` | Keyboard shortcuts |

Tables support alignment:

| Feature | Status | Notes |
|:--------|:------:|------:|
| Markdown editing | Active | Monaco-powered |
| Live preview | Active | Scroll-synced |
| Cloud sync | Available | 5 providers |
| PDF export | Available | Server-rendered |

## Footnotes

Dillinger supports extended markdown syntax including footnotes[^1] and definition lists.

[^1]: Footnotes appear at the bottom of the rendered preview.

## Math

Inline math: $E = mc^2$

Block equations:

$$
\sum_{i=1}^{n} i = \frac{n(n+1)}{2}
$$

---

*Your documents save automatically. Start writing.*
