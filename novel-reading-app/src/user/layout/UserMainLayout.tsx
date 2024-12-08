import React, { ReactNode } from 'react';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';

interface UserLayoutProps {
    children: ReactNode;
}

const UserMainLayout: React.FC<UserLayoutProps> = ({ children }) => {
    return (
        <>
            <Header />
            <div className="content">{children}</div>
            <Footer />
        </>
    );
};

export default UserMainLayout;
