<!DOCTYPE html>

<html>

  <head>
    <title>Thymeleaf Sandbox: Spring Reactive</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>

  <body>

    <p>Using template engine: <strong>FreeMarker</strong></p>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Age</th>
        </tr>
      </thead>
      <tbody>
<#list dataSource as e>        <tr>
          <td>${e.id?html}</td>
          <td>${e.name?html}</td>
          <td>${e.age?html}</td>
        </tr>
</#list>
      </tbody>
    </table>

  </body>

</html>