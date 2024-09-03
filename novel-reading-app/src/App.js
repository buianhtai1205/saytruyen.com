import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import HomePage from './pages/HomePage/HomePage';
import StoryDetail from './pages/StoryDetail/StoryDetail';
import './App.css';

function App() {
    return (
        <Router>
            <div className="app">
                <Header />
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/story/:id" element={<StoryDetail />} />
                </Routes>
                <Footer />
            </div>
        </Router>
    );
}

export default App;
