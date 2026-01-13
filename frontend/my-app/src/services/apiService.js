// Placeholder pentru serviciile API
// Momentan returnează mock data, va fi implementat cu apeluri reale către backend

import { offices, documentCategories } from '../mocks/mockData';

// Serviciu placeholder pentru apeluri API
class ApiService {
  constructor() {
    this.baseUrl = 'http://localhost:8080/api'; // URL-ul va fi configurat mai târziu
  }

  // Obține lista de birouri
  async getOffices() {
    // Simulează un delay de rețea
    await this.delay(300);
    return offices;
  }

  // Obține categoriile de documente
  async getDocumentCategories() {
    await this.delay(300);
    return documentCategories;
  }

  // Obține dependențele pentru un document
  async getDocumentDependencies(documentName) {
    await this.delay(200);
    // Va fi implementat cu apel real
    return [];
  }

  // Trimite o cerere pentru un document
  async submitDocumentRequest(documentData) {
    await this.delay(500);
    // Va fi implementat cu apel real
    return {
      success: true,
      requestId: Math.random().toString(36).substring(7),
      message: 'Cerere înregistrată cu succes',
    };
  }

  // Obține cererile utilizatorului
  async getUserRequests(userId) {
    await this.delay(300);
    // Va fi implementat cu apel real
    return [];
  }

  // Helper pentru simularea delay-ului
  delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

export default new ApiService();
