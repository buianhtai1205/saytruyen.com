import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import Banner from './components/Banner/Banner';
import BookList from './components/BookList/BookList';
import Top from './components/Top/Top';
import NewChapters from './components/NewChapters/NewChapters';
import NewCompletedStory from './components/NewCompletedStory/NewCompletedStory';
import Evaluation from './components/Evaluation/Evaluation';
import Footer from './components/Footer/Footer';
import './App.css';

function App() {
    return (
        <Router>
            <div className="app">
                <Header />
                <Routes>
                    <Route
                        path="/"
                        element={
                            <>
                                <Banner
                                    imageUrl="https://static.cdnno.com/storage/topbox/1f84ac535622a55309d6da9fcc874397.webp"
                                    linkUrl="/story-detail"
                                    altText="Lam Ruong Duc Yeu"
                                />
                                <BookList />
                                <Banner
                                    imageUrl="https://static.cdnno.com/storage/topbox/cfa131557675a907cb466491cd7a1e72.webp"
                                    linkUrl="/story-detail"
                                    altText="Lam Ruong Duc Yeu"
                                />
                                <Top />
                                <NewChapters />
                                <Banner
                                    imageUrl="https://static.cdnno.com/storage/topbox/b0e8db3d667ca42b92169815b0b6ed41.webp"
                                    linkUrl="/story-detail"
                                    altText="Lam Ruong Duc Yeu"
                                />
                                <NewCompletedStory />
                                <Evaluation />
                            </>
                        }
                    />
                    {/* Add more routes here as needed */}
                    <Route
                        path="/story/:id"
                        element={<div>Story Detail Page</div>}
                    />
                </Routes>
                <Footer />
            </div>
        </Router>
    );
}

export default App;
