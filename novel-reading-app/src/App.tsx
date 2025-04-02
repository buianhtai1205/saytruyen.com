import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import UserMainLayout from './user/layout/UserMainLayout';
import AdminMainLayout from './admin/layout/AdminMainLayout';
import HomePage from './user/pages/HomePage/HomePage';
import StoryDetail from './user/pages/StoryDetail/StoryDetail';
import CommonComponent from './user/pages/CommonComponent/CommonComponent';
import Dashboard from './admin/components/Dashboard/Dashboard';
import Table from './admin/components/Table/Table';
import Story from './admin/components/Story/Story';
import ChapterDetail from './user/pages/ChapterDetail/ChapterDetail';
import { AuthProvider } from './contexts/auth';

function App() {
    return (
        <AuthProvider>
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
                            path="/truyen/:nameWithId"
                            element={
                                <UserMainLayout>
                                    <StoryDetail />
                                </UserMainLayout>
                            }
                        />
                        <Route
                            path="/truyen/:nameWithId/chuong/:chapterId"
                            element={
                                <UserMainLayout>
                                    <ChapterDetail />
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
                    <Routes>
                        <Route
                            path="/admin"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/stories"
                            element={
                                <AdminMainLayout>
                                    <Story />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/story/:id"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/story/chapters"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/story/chapter/:id"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/users"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/user/:id"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/uikit/table"
                            element={
                                <AdminMainLayout>
                                    <Table />
                                </AdminMainLayout>
                            }
                        />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}

export default App;
