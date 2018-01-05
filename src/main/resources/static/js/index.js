
// Poblar la tabla al inicio
$(document).ready(function () {
    $.getJSON("/note/find", function (notes) {
        $.each(notes, function (i, note) {
            addNoteToTable(note);
        });
    });
});

// Borrar elemento de la tabla
$(document).ready(function () {
    $('#table0').on('click', 'td.delete', function () {
        var id = $(this).parent().attr("id");
        $.ajax({url: "/note?id=" + id,
            type: "DELETE",
            error: function () {
                alert("Error haciendo DELETE");                
            },
            success: function () {                
                $("tr#" + id).remove();
            }
        });

    });
});

// Incluir nuevo elemento en la tabla
$(document).ready(function () {
    $("#button0").click(function () {
        var payload = new Object();
        payload.text = $("#input0").val();
        $.ajax({url: "/note",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(payload),
            error: function () {
                alert("Error haciendo POST");
            },
            success: function (receivedData) {
                addNoteToTable(receivedData);
                $("#input0").val("");
            }
        });
    });
});

function addNoteToTable(note) {
    var $tr = $('<tr>');
    $tr.attr("id", note.id);
    $tr.append($('<td class="text">').text(note.text));
    $tr.append($('<td class="delete">').text("X"));
    $("#table0").append($tr);
}
