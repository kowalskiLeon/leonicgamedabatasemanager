import React, { useState } from "react";

import { Container, Row, Col, Card, ListGroup, Button, Collapse } from "react-bootstrap";

class TelaInicial extends React.Component {
    render() {

        const tela = <RetornarTela />;

        return tela;
    }
}

function RetornarTela() {
    const [open, setOpen] = useState(false);
    return (<Container fluid>
        <Row>
            <Col sm='12' lg='4'>
                <h1 className='mt-4 text-center'>Welcome</h1>
            </Col>
        </Row>

        <Row className='mt-5'>
            <Col lg='1' sm='0'></Col>
            <Col sm='12' lg='4'>
                <Card>
                    <Card.Header>Recent Projects</Card.Header>
                    <Card.Body>
                        <ListGroup >
                            <ListGroup.Item><a href='#'>Projeto 1</a></ListGroup.Item>
                            <ListGroup.Item><a href='#'>Projeto 2</a></ListGroup.Item>
                            <ListGroup.Item><a href='#'>Projeto 3</a></ListGroup.Item>
                            <ListGroup.Item><a href='#'>Projeto 4</a></ListGroup.Item>
                            <ListGroup.Item><a href='#'>Projeto 5</a></ListGroup.Item>
                        </ListGroup>
                    </Card.Body>
                </Card>
            </Col>
            <Col sm='12' lg='6'>
                <Row>
                    <Col sm='4' lg='4'><Button variant="info" className='w-100'
                        onClick={() => setOpen(!open)}
                        aria-controls="example-collapse-text"
                        aria-expanded={open}
                    > Novo Projeto</Button></Col>
                    <Col sm='4' lg='4'><Button variant="info" className='w-100'> Editar Projeto</Button></Col>
                    <Col sm='4' lg='4'><Button variant="info" className='w-100'> Remover Projeto</Button></Col>
                </Row>
                <Row>
                    <Collapse in={open}>
                        <Card className='mt-5'>
                            <h3>New Database</h3>
                            <Card.Body>
                                <Col sm='12' lg='6'>
                                    <Row className='mt-2'><input placeholder="Name"></input></Row>
                                    <Row className='mt-2'><input placeholder="Author"></input></Row>
                                    <Row className='mt-2'>
                                        <select>
                                            <option>Default Language</option>
                                            <option>Portuguese</option>
                                            <option>English</option>
                                        </select>
                                    </Row>
                                    <Row className='mt-3'>
                                        <Col sm='4' lg='4'><Button variant="info" className='w-100'> Salvar</Button></Col>
                                        <Col sm='4' lg='4'><Button variant="info" className='w-100'
                                            onClick={() => setOpen(!open)}
                                            aria-controls="example-collapse-text"
                                            aria-expanded={open}
                                        > Cancelar</Button></Col>
                                    </Row>
                                </Col>
                            </Card.Body>
                        </Card>
                    </Collapse>
                </Row>
            </Col>
            <Col lg='1' sm='0'></Col>
        </Row>
    </Container>);
}

export default TelaInicial;