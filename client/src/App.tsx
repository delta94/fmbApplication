import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import AppRouter from './components/AppRouter/AppRouter';
import GlobalErrorHandler from './components/ErrorHandler/GlobalErrorHandler';

class App extends Component {
  render() {
    return (
      <div className ="container-fluid">
       <GlobalErrorHandler>
       <AppRouter/>
       </GlobalErrorHandler> 
      </div>
    );
  }
}

export default App;
