import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useEffect, useState } from 'react';
import authService from "./AuthService";
import { Link,Navigate,useNavigate } from 'react-router-dom';


const theme = createTheme();

export default function SignUp() {

  const [showError, setShowError] = useState<Boolean>(false);
  const [errorMessage, setErrorMessage] = useState<string>("");
  const [registerSuccessful, setRegisterSuccessful] =useState<Boolean>(false);  
  const navigate = useNavigate();
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    setShowError(false);
    setErrorMessage("");
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    let username = data.get('username')?.toString();
    let password = data.get('password')?.toString();
    let passwordRepeat = data.get('passwordRepeat')?.toString();
    let email = data.get('email')?.toString();
 
    if(passwordRepeat!==password){
      setShowError(true);
      setErrorMessage("Passwords do not match!");
      return;
    }
    if(username===""){
      setShowError(true);
      setErrorMessage("Username can't be empty!");
      return;
    } 
    if(email===""){
      setShowError(true);
      setErrorMessage("Email can't be empty!");
      return;
    }

    if(password===""){
      setShowError(true);
      setErrorMessage("Password can't be empty!");
      return;
    }

    
    if(username!==null &&  email!==null && password!==null &&username!==undefined &&  email!==undefined&& password!==undefined){
      authService.register(username,email,password)
      .then(()=>  navigate("/"))
      .catch(error => {
        setShowError(true);
        setErrorMessage("That username or email already exist!");
      });  
     
    } 
  };

  const goSignIn=()=>{
    navigate('/')
    navigate('signIn')
  }

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
            {showError &&
              <Grid item xs={12}>
                <Stack sx={{ width: '100%' }} spacing={2}>
                <Alert severity="error">{errorMessage}</Alert>
                </Stack>
              </Grid>
            }
              <Grid item xs={12}>
                <TextField
                  autoComplete="given-name"
                  name="username"
                  required
                  fullWidth
                  id="username"
                  label="Username"
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="passwordRepeat"
                  label="Repeat password"
                  type="password"
                  id="passwordRepeat"
                  autoComplete="new-password2"
                />
              </Grid>
          
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Button onClick={goSignIn}>
                  Already have an account? Sign in
                </Button>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

