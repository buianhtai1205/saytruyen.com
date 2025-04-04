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
import Page404 from './user/pages/ErrorPage/Page404';
import Page500 from './user/pages/ErrorPage/Page500';

function App() {
    return (
        <AuthProvider>
            <Router>
                <div className="app">
                    <Routes>
                        {/* User routes */}
                        <Route
                            path="/"
                            element={
                                <UserMainLayout>
                                    <HomePage />
                                </UserMainLayout>
                            }
                        />
                        <Route
                            path="/common-component"
                            element={<CommonComponent />}
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

                        {/* Admin routes */}
                        <Route
                            path="/admin"
                            element={
                                <AdminMainLayout>
                                    <Dashboard />
                                </AdminMainLayout>
                            }
                        />
                        <Route
                            path="/admin/common-component"
                            element={
                                <AdminMainLayout>
                                    <CommonComponent />
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

                        <Route
                            path="/internal-server-error"
                            element={<Page500 />}
                        />

                        {/* 404 route - must be last */}
                        <Route path="*" element={<Page404 />} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}

export default App;
