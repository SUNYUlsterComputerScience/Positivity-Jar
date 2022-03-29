import React from 'react'
import {
  BrowserRouter,
  Router,
  Route,
  Link,
} from "react-router-dom";

import LandingPage from './components/pages/LandingPage';
import LoginPage from './components/pages/LoginPage';
import RegisterPage from './components/pages/RegisterPage';
import ForgetPasswordPage from './components/pages/ForgetPasswordPage';
import HomePage from './components/pages/HomePage';
import Footer from './components/pages/Footer';
import './App.css';

function App() {
  return (

     <div>
       
      <LoginPage />
       
       <Footer />
     </div>

  );
}

export default App;
