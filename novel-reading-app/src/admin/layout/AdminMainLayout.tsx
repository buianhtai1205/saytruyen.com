import React from 'react';
import Layout from './Layout';
import { PrimeReactProvider } from 'primereact/api';
import { LayoutProvider } from './context/LayoutContext';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';
import '../../styles/layout/layout.scss';
import '../../styles/demo/Demos.scss';

interface AppLayoutProps {
    children: React.ReactNode;
}

const AppLayout: React.FC<AppLayoutProps> = ({ children }) => {
    return (
        <PrimeReactProvider>
            <LayoutProvider>
                <Layout>{children}</Layout>
            </LayoutProvider>
        </PrimeReactProvider>
    );
};

export default AppLayout;
