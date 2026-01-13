// Mock data pentru sistemul Biroul.ro
// Acest fișier va fi folosit pentru a simula datele până când backend-ul va fi integrat

export const offices = [
  {
    id: 1,
    name: 'Direcția de Evidență a Persoanelor',
    shortName: 'DEP',
    description: 'Emite acte de identitate, certificate de naștere, căsătorie, deces',
    counters: 4,
    avgProcessingTime: '15-30 minute',
  },
  {
    id: 2,
    name: 'Administrația Financiară Locală',
    shortName: 'AFL',
    description: 'Emite certificate fiscale, atestări de plată taxe și impozite',
    counters: 3,
    avgProcessingTime: '20-45 minute',
  },
  {
    id: 3,
    name: 'Oficiul de Cadastru și Publicitate Imobiliară',
    shortName: 'OCPI',
    description: 'Emite extrase de carte funciară, dovezi de proprietate',
    counters: 2,
    avgProcessingTime: '30-60 minute',
  },
  {
    id: 4,
    name: 'Serviciul de Înmatriculări Auto',
    shortName: 'SIA',
    description: 'Emite certificate de înmatriculare, permise auto',
    counters: 3,
    avgProcessingTime: '25-40 minute',
  },
  {
    id: 5,
    name: 'Oficiul Registrului Comerțului',
    shortName: 'ORC',
    description: 'Emite certificate de înregistrare firmă, extrase',
    counters: 2,
    avgProcessingTime: '20-35 minute',
  },
];

export const documentCategories = [
  {
    id: 1,
    name: 'Acte de identitate',
    documents: [
      'Carte de identitate',
      'Pașaport',
      'Certificat de naștere',
      'Certificat de căsătorie',
    ],
    officeId: 1,
  },
  {
    id: 2,
    name: 'Certificate fiscale',
    documents: [
      'Certificat fiscal',
      'Certificat atestare fiscală',
      'Adeverință plată taxe locale',
    ],
    officeId: 2,
  },
  {
    id: 3,
    name: 'Documente imobiliare',
    documents: [
      'Extract carte funciară',
      'Certificat de sarcini',
      'Adeverință proprietate',
    ],
    officeId: 3,
  },
  {
    id: 4,
    name: 'Documente auto',
    documents: [
      'Certificat de înmatriculare',
      'Permis de conducere',
      'Talon auto',
    ],
    officeId: 4,
  },
  {
    id: 5,
    name: 'Documente comerciale',
    documents: [
      'Certificat de înregistrare firmă',
      'Extract ORC',
      'Certificat constatator',
    ],
    officeId: 5,
  },
];

// Exemplu de dependențe între documente
export const documentDependencies = {
  'Certificat atestare fiscală': [
    'Carte de identitate',
    'Adeverință domiciliu',
  ],
  'Extract carte funciară': [
    'Carte de identitate',
    'Certificat atestare fiscală',
  ],
  'Certificat de înmatriculare': [
    'Carte de identitate',
    'Certificat fiscal',
    'Adeverință de plată taxe',
  ],
  'Certificat de înregistrare firmă': [
    'Carte de identitate',
    'Certificat fiscal',
    'Extract carte funciară',
  ],
};

// Statusuri posibile pentru cereri
export const requestStatuses = {
  PENDING: 'În așteptare',
  IN_QUEUE: 'În coadă',
  PROCESSING: 'În procesare',
  ON_BREAK: 'Pauză funcționar',
  COMPLETED: 'Finalizat',
  FAILED: 'Eșuat',
};
