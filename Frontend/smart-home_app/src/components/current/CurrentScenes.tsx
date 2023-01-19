import { Button, Card, CardActionArea, CardContent, CardMedia, Grid, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Scene } from "../../interfaces";

export interface CurrentScenesProps{
    scenes?:Scene[];
}

export default function CurrentScenes(props?:CurrentScenesProps) {

    const [scenes, setScenes] =  useState<Scene[]>();

    useEffect(() => {
        if(props!=undefined && props?.scenes!=undefined)
            setScenes(props?.scenes)
    }, []);

    const handleSceneClicked=(scene:Scene)=>{}

    return (
        <Grid justifyContent="center" container item xs={12}>
            <Grid justifyContent="center" container item xs={12}>
                <Typography  variant="h6" >Your current scenes</Typography>
            </Grid>
            <br></br>
            <br></br>
            <br></br>
            <Grid justifyContent="center" container item xs={12}>
                <Grid container justifyContent="center" spacing={3}>
                {scenes?.map((scene)=>
                    <Button onClick={()=>handleSceneClicked(scene)}>
                        <Link to={'/scene/'+scene.id}>
                            <Grid item>
                                <Card>
                                    <CardActionArea>
                                        <CardMedia
                                        src="https://img.freepik.com/free-photo/d-rendering-black-background-product-podium-stand-studio_1258-112104.jpg?w=1380&t=st=1672922145~exp=1672922745~hmac=32195b1a4b538e84d8775ad1251285b1f0905f48b66f8875b93b5358166fa7dc"
                                        component="img"
                                        height="150"
                                        />
                                        <CardContent>
                                        <Typography gutterBottom variant="h5" component="div">
                                            {scene.name } 
                                        </Typography>
                                        </CardContent>
                                    </CardActionArea>
                                </Card>
                            </Grid>
                        </Link>
                    </Button>
                )}
                </Grid>
            </Grid> 
        </Grid> 

      );
}
