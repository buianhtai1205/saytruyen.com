'use client';
import React, { ReactNode } from 'react';
import { LayoutProvider } from './context/LayoutContext';
import { PrimeReactProvider } from 'primereact/api';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';
import '../../styles/layout/layout.scss';
import '../../styles/demo/Demos.scss';

interface AdminLayoutProps {
    children: ReactNode;
}

const AdminMainLayout2: React.FC<AdminLayoutProps> = ({ children }) => {
    return (
        <PrimeReactProvider>
            <LayoutProvider>{children}</LayoutProvider>
        </PrimeReactProvider>
    );
};

export default AdminMainLayout2;
