import {Alert,Box,Button,FormControl,FormControlLabel,FormLabel,Grid,LinearProgress,Radio,RadioGroup,Stack,Table,TableBody,TableCell,TableRow,TextField,Typography,} from "@mui/material";
import { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import AuthService from "../authorization/AuthService";
import { HouseUserDto} from "../interfaces";
import HouseUserService from "../Services/HouseUserService";

  
export default function HouseUserComponent() {
    
    const onTextChange = (e: any) => setNewUser(e.target.value);
    const [showError, setShowError] = useState<Boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>("");
    const [loading,setLoading] =useState<boolean>(true);
    const [userList, setUserList] = useState<HouseUserDto[]>();
    const [newUser, setNewUser] = useState<string>();
    const {id} = useParams()
    const [me, setMe] =useState<HouseUserDto>();

    useEffect(() => {
        fetchData();
    }, []);

    useEffect(() => {
        setMe(userList?.filter(user=>(user.userId == AuthService.getLoggedUser().id))[0]);              
    }, [loading]);

    const fetchData=async()=>{
        if(id!==undefined){
            let response = await HouseUserService.fetchHouseUsers(id);
            setUserList(response);
            setLoading(false)          
        }
    }
    

    const handleAddHomeUser=()=>{
        if(id!==undefined && newUser!==undefined)
            HouseUserService.addHouseUser(id,newUser);
    }

    const setUserRole = (event:any, value:any, userId:number) => {
        if(value!==undefined && userId!==undefined && id !==undefined)
            HouseUserService.setUserRole(value,userId,id);
      };

    const generateRadio=(dto:HouseUserDto)=>{
        if(me?.role==='Admin'){
            return (
                    <FormControl>
                        <FormLabel id="demo-radio-buttons-group-label">Role</FormLabel>
                            <RadioGroup
                                aria-labelledby="demo-radio-buttons-group-label"
                                defaultValue={dto.role}
                                name="radio-buttons-group"
                                onChange={(e,value) => setUserRole(e,value,dto.userId)}
                            >
                            <FormControlLabel value="Guest" control={<Radio />} label="Guest" />
                            <FormControlLabel value="Resident" control={<Radio />} label="Resident" />
                        </RadioGroup>
                    </FormControl>
  
            )
        }
        else{
            return (<Typography>{dto.role} </Typography>)
        }                
    }

    const generateTableRow = (dto:HouseUserDto) => {   
        if(me?.userId!=dto.userId){
            return (       
                <TableRow key={dto.userId}>
                    <TableCell>{dto.username}</TableCell>
                    <TableCell>{generateRadio(dto)}</TableCell>
                </TableRow>
            )   
        }          
    }
    
    return (
        <Grid container spacing={3}>
            {!loading ?<>
                {me?.role==='Admin' &&
                    <><Grid justifyContent="center" container item xs={12}>
                        <Typography variant="h6">Add new home user</Typography>
                    </Grid><Grid justifyContent="center" container item xs={12}>
                            {showError &&
                                <Grid item xs={12}>
                                    <Stack sx={{ width: '100%' }} spacing={2}>
                                        <Alert severity="error">{errorMessage}</Alert>
                                    </Stack>
                                </Grid>}
                            <TextField onChange={onTextChange} value={newUser} label={"Username"} />
                            <Button variant="contained" onClick={() => handleAddHomeUser()}>Add User to house</Button>
                        </Grid>
                        
                        <Grid justifyContent="center" container item xs={12}>
                            <Link to={'/house/permission/'+ id}>
                                <Button variant="contained">Manage role permission</Button>     
                            </Link>
                                
                        </Grid>
                    </>
                }
                <Grid justifyContent="center" container item xs={12}>
                    <Typography  variant="h6" >Current house users</Typography>

                </Grid>    

                <Grid justifyContent="center" container item xs={12}>
                    <Table>
                        <TableBody>
                            {userList?.map(user => (
                                generateTableRow(user)
                            ))}
                        </TableBody>
                    </Table>
                </Grid>
            </>
            :
            <Box sx={{ width: '100%' }}>
                <LinearProgress />
            </Box>
            
        }

        </Grid>
    );  
};
