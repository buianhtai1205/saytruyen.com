import React, { ReactNode } from 'react';

interface UserLayoutProps {
    children: ReactNode;
}

const UserMainLayout: React.FC<UserLayoutProps> = ({ children }) => {
    return (
        <>
            <div className="admin-content">{children}</div>
        </>
    );
};

export default UserMainLayout;
