import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UserMainLayout from './user/layout/UserMainLayout';
import AdminMainLayout from './admin/layout/AdminMainLayout';
import HomePage from './user/pages/HomePage/HomePage';
import StoryDetail from './user/pages/StoryDetail/StoryDetail';
import CommonComponent from './user/pages/CommonComponent/CommonComponent';
import './App.css';

function App() {
    return (
        <Router>
            <div className="app">
                {/* User routes */}
                <Routes>
                    <Route
                        path="/common-component"
                        element={<CommonComponent />}
                    />
                    <Route
                        path="/"
                        element={
                            <UserMainLayout>
                                <HomePage />
                            </UserMainLayout>
                        }
                    />
                    <Route
                        path="/story/:id"
                        element={
                            <UserMainLayout>
                                <StoryDetail />
                            </UserMainLayout>
                        }
                    />
                </Routes>

                {/* Admin routes */}
                <Routes>
                    <Route
                        path="/admin/common-component"
                        element={
                            <AdminMainLayout>
                                <CommonComponent />
                            </AdminMainLayout>
                        }
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
