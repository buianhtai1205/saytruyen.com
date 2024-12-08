import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import GlobalStyles from './user/components/GlobalStyles/GlobalStyles';

const rootElement = document.getElementById('root');
if (!rootElement) {
    throw new Error('Failed to find the root element');
}
const root = ReactDOM.createRoot(rootElement);

// Render the App component into the root element
root.render(
    // <React.StrictMode>
    <GlobalStyles>
        <App />
    </GlobalStyles>
    // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
