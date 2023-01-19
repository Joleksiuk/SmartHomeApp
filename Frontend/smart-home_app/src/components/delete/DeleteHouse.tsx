import Button  from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import { useEffect, useState } from 'react';
import {  HouseDto, Scene } from '../../interfaces';
import { Grid } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import HomeService from '../../Services/HomeService';
import DeleteModal from './DeleteModal';


export interface DeleteHouseProps{
    houseDto?: HouseDto,
}
  
export default function DeleteHouse(props:DeleteHouseProps) {

    const [confirmDelete, setConfirmDelete] = useState<boolean>(false);
    const [deleteMessage, setDeleteMessage] = useState<string>('');
    const [openDeleteModal, setOpenDeleteModal] = useState<boolean>(false);
    const navigate = useNavigate();
    
    const onDeleteHouseClicked=()=>{
       if(props.houseDto?.house?.name!==undefined){
            setOpenDeleteModal(true);
            setDeleteMessage('Do you really want to delete house: '+ props.houseDto.house.name+' ?');
       }
    }

    const deleteHouse=()=>{
        if(props.houseDto?.house?.id!=undefined)
            HomeService.deleteHome(props.houseDto.house?.id)
    }

    useEffect(() => {  
        if(props===undefined )
            return;    
        if(confirmDelete===true){
            deleteHouse()
            setConfirmDelete(false);
            navigate('/manageHomes')  
        }
    }, [confirmDelete]);

    return (
        <Grid container justifyContent="center" item xs={12}>  
            <DeleteModal setDeleteConfirm={setConfirmDelete} open={openDeleteModal} message={deleteMessage} setModalOpen={setOpenDeleteModal}/>
            <Button variant="outlined"  onClick = {onDeleteHouseClicked} startIcon={<DeleteIcon /> }>Delete house</Button>
        </Grid>
    );
}