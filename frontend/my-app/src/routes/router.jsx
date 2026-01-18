import { createBrowserRouter } from 'react-router-dom';
import Layout from '../layout/Layout';
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import OfficesPage from '../pages/OfficesPage';
import ObtainDocumentPage from '../pages/ObtainDocumentPage';
import ObtainedDocumentsPage from '../pages/ObtainedDocumentsPage';
import ProtectedRoute from "./ProtectedRoute";

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    children: [
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: 'login',
        element: <LoginPage />,
      },
      {
        path: 'birouri',
        element: <OfficesPage />,
      },
      {
        path: 'obtinere',
        element: <ObtainDocumentPage />,
      },
      {
        path: 'documente',
        element: <ObtainedDocumentsPage />,
      },
      {
        path: "documente",
        element: (
          <ProtectedRoute>
            <ObtainedDocumentsPage />
          </ProtectedRoute>
        ),
      },
    ],
  },
]);
