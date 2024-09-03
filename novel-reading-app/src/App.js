import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import HomePage from './pages/HomePage/HomePage';
import './App.css';

function App() {
    return (
        <Router>
            <div className="app">
                <Header />
                <Routes>
                    <Route path="/" element={<HomePage />} />
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
