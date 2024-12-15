import React, { ReactNode } from 'react';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';
import GlobalStyles from '../components/GlobalStyles/GlobalStyles';

interface UserLayoutProps {
    children: ReactNode;
}

const UserMainLayout: React.FC<UserLayoutProps> = ({ children }) => {
    return (
        <>
            <GlobalStyles>
                <Header />
                {children}
                <Footer />
            </GlobalStyles>
        </>
    );
};

export default UserMainLayout;
