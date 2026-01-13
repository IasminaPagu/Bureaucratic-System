# Biroul.ro - Frontend

Aplicație web React + Vite pentru simularea sistemului birocratic românesc.

## Structura Proiectului

```
src/
├── layout/              # Layout-uri partajate
│   ├── Layout.jsx       # Layout principal cu navbar
│   └── Layout.module.css
├── pages/              # Pagini pentru fiecare rută
│   ├── HomePage.jsx    # Pagina principală (implementată complet)
│   ├── HomePage.module.css
│   ├── LoginPage.jsx   # Placeholder autentificare
│   ├── OfficesPage.jsx # Placeholder lista birouri
│   ├── ObtainDocumentPage.jsx  # Placeholder obținere document
│   ├── ObtainedDocumentsPage.jsx # Placeholder documente obținute
│   └── CommonPage.module.css
├── routes/             # Configurarea rutelor
│   └── router.jsx
├── components/         # Componente refolosibile (pentru viitor)
├── services/           # Servicii pentru API
│   └── apiService.js   # Placeholder serviciu API
├── mocks/              # Date mock pentru dezvoltare
│   └── mockData.js     # Birouri, documente, dependențe
└── styles/             # Stiluri globale
    └── global.css
```

## Tehnologii

- **React 19.2** - Library UI
- **Vite 7.3** - Build tool și dev server
- **react-router-dom** - Routing SPA
- **CSS Modules** - Stilizare componentă
- **JavaScript** (nu TypeScript)

## Rulare Aplicație

```bash
# Instalare dependențe
npm install

# Pornire server dezvoltare
npm run dev

# Build pentru producție
npm build
```

Aplicația va rula pe `http://localhost:5173/`

## Rute Disponibile

- `/` - Pagina principală (HomePage)
- `/login` - Autentificare (placeholder)
- `/birouri` - Lista birouri publice (placeholder)
- `/obtinere` - Obținere document nou (placeholder)
- `/documente` - Documente obținute (placeholder)

## Caracteristici Implementate

✅ Structură modulară cu separarea responsabilităților  
✅ Routing cu react-router-dom  
✅ Layout partajat cu navbar  
✅ Active route highlighting în navbar  
✅ Pagina principală completă cu informații despre sistem  
✅ Mock data pentru birouri și documente  
✅ Serviciu API placeholder pentru integrare viitoare  
✅ CSS Modules pentru stilizare  
✅ Toate textele în limba română cu diacritice  

## Note Dezvoltare

- Backend-ul este separat (Spring Boot services)
- Frontend-ul folosește momentan mock data din `src/mocks/mockData.js`
- Serviciul API (`src/services/apiService.js`) va fi extins pentru a face apeluri reale
- Nu se folosesc UI libraries externe (Material-UI, Ant Design, etc.)
- Stilizarea este făcută cu CSS simplu și CSS Modules

## Următorii Pași

1. Implementarea paginilor placeholder (Login, Birouri, etc.)
2. Conectarea la backend prin serviciul API
3. Adăugarea componentelor refolosibile în `src/components/`
4. Implementarea gestionării stării (Context API sau alt state management)
5. Adăugarea autentificării și autorizării
